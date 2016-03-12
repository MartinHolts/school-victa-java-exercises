import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.FileInputStream;



public class Map {

	private final int TILE_PATH = 0;
	private final int TILE_WALL = 1;
	private final int TILE_BOX = 2;
	private final int TILE_TARGET = 3;
	private final int TILE_CHARACTER = 4;

	private final int TILE_WIDTH = 30;
	private final int TILE_HEIGHT = 30;
	private final int TILE_SEPARATION = 5;

	private final int CHARACTER_SIZE = TILE_WIDTH / 2;
	private final int WALL_SIZE = TILE_WIDTH / 3;
	private final int BOX_SIZE = TILE_WIDTH / 4;
	private final int TARGET_SIZE = TILE_WIDTH / 5;

	private int rows;
	private int columns;

	private int[][] map;

	private int characterColumn = 3;
	private int characterRow = 0;

	private int wallColumn = 7;
	private int wallRow = 2;

	private int boxColumn = 5;
	private int boxRow = 2;

	private int targetColumn = 3;
	private int targetRow = 4;

	public Map(String fileName) {
    	try {
            FileInputStream in = new FileInputStream(fileName); // Same location as .class file basically.
            rows = in.read();
            columns = in.read();
			System.out.println(rows);
			System.out.println(columns);

			map = new int[rows][columns];

			for (int row = 0; row < rows; row++) {
				for (int column = 0; column < columns; column++) {
					map[row][column] = in.read();
				}
			}		

			if (in != null) {
                in.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
	}


	public Map(int rows, int columns) {
		this.rows = rows;		
		this.columns = columns;
		map = new int[rows][columns];

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				map[row][column] = TILE_PATH;
				/*
				if (row == 1) {
					map[row][column] = TILE_WALL;
				}*/
			}
		}
	}

	public int getWidth() {
		return (columns * TILE_WIDTH) + ((columns - 1) * TILE_SEPARATION);
	}

	public int getHeight() {
		return (rows * TILE_HEIGHT) + ((rows - 1) * TILE_SEPARATION);
	}

	public void update(int keyCode) {
		if (keyCode == KeyEvent.VK_RIGHT) {
			characterColumn++;
			if (characterColumn == wallColumn && characterRow == wallRow) {
				characterColumn--;
			}
			if (characterColumn == boxColumn && characterRow == boxRow) {
				boxColumn++;
				if (boxColumn == wallColumn && boxRow == wallRow) {
					boxColumn--;
					characterColumn--;
				}
			}
		}
		else if (keyCode == KeyEvent.VK_LEFT ) {
			characterColumn--;
			if (characterColumn == wallColumn && characterRow == wallRow) {
				characterColumn++;
			}
			if (characterColumn == boxColumn && characterRow == boxRow) {
				boxColumn--;
				if (boxColumn == wallColumn && boxRow == wallRow) {
					boxColumn++;
					characterColumn++;
				}
			}
		}
		else if (keyCode == KeyEvent.VK_UP ) {
			characterRow--;
			if (characterColumn == wallColumn && characterRow == wallRow) {
				characterRow++;
			}
			if (characterColumn == boxColumn && characterRow == boxRow) {
				boxRow--;
				if (boxColumn == wallColumn && boxRow == wallRow) {
					boxRow++;
					characterRow++;
				}
			}
		}
		else if (keyCode == KeyEvent.VK_DOWN ) {
			characterRow++;
			if (characterColumn == wallColumn && characterRow == wallRow) {
				characterRow--;
			}
			if (characterColumn == boxColumn && characterRow == boxRow) {
				boxRow++;
				if (boxColumn == wallColumn && boxRow == wallRow) {
					boxRow--;
					characterRow--;
				}
			}
		}
		if (boxColumn == targetColumn && boxRow == targetRow) {
			System.out.println("Good Job!");
		}
	}


	public void draw(Graphics g, int x, int y) {
		//g.setColor(Color.RED);
		//g.fillRect(x, y, 100, 100);

		int offsetX = 0;
		int offsetY = 0;

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				Color color = getTileColor(map[row][column]);
				g.setColor(color);
				g.fillRect(x + offsetX, y + offsetY, TILE_WIDTH, TILE_HEIGHT);				

				if (row == characterRow && column == characterColumn) {
					g.setColor(Color.GREEN);				
					int characterX = x + offsetX + (TILE_WIDTH / 2) - (CHARACTER_SIZE / 2);
					int characterY = y + offsetY + (TILE_HEIGHT / 2) - (CHARACTER_SIZE / 2);
					g.fillRect(characterX, characterY, CHARACTER_SIZE, CHARACTER_SIZE);
				}
				if (row == wallRow && column == wallColumn) {
					g.setColor(Color.GREEN);				
					int wallX = x + offsetX + (TILE_WIDTH / 2) - (WALL_SIZE / 2);
					int wallY = y + offsetY + (TILE_HEIGHT / 2) - (WALL_SIZE / 2);
					g.fillRect(wallX,wallY, WALL_SIZE, WALL_SIZE);
				}
				if (row == boxRow && column == boxColumn) {
					g.setColor(Color.GREEN);				
					int boxX = x + offsetX + (TILE_WIDTH / 2) - (BOX_SIZE / 2);
					int boxY = y + offsetY + (TILE_HEIGHT / 2) - (BOX_SIZE / 2);
					g.fillRect(boxX,boxY, BOX_SIZE, BOX_SIZE);
				}
				if (row == targetRow && column == targetColumn) {
					g.setColor(Color.GREEN);				
					int targetX = x + offsetX + (TILE_WIDTH / 2) - (TARGET_SIZE / 2);
					int targetY = y + offsetY + (TILE_HEIGHT / 2) - (TARGET_SIZE / 2);
					g.fillRect(targetX,targetY, TARGET_SIZE, TARGET_SIZE);
				}

				offsetX += TILE_WIDTH + TILE_SEPARATION;
			}
			offsetX = 0;
			offsetY += TILE_HEIGHT + TILE_SEPARATION;
		}
	}

	private Color getTileColor(int tileValue) {
		switch (tileValue) {
			case TILE_PATH:
				return Color.WHITE;
			case TILE_WALL:
				return Color.GRAY;
			case TILE_BOX:
				return Color.ORANGE;
			case TILE_TARGET:
				return Color.GREEN;
			case TILE_CHARACTER:
				return Color.RED;
		}
		return Color.BLUE;
	}
}