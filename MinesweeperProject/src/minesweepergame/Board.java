package minesweepergame;

public class Board {
	
	public Cell[][] cells;
	public Cell[] mines;
	public int size;
	public int totalMines;
	public int totalRevealed;
	public int totalCells;
	public boolean gameOver = false;
	
	public Board(int size, int totalMines) {
		this.size = size;
		this.totalCells = size * size;
		this.totalMines = totalMines;
		this.cells = new Cell[size][size];
		this.mines = new Cell[totalMines];
		this.reset();
	}
	
	public void reset() {
		this.totalRevealed = 0;
		this.gameOver = false;
		
		// set the board
		for (int x = 0; x < this.size; x++) {
			for (int y = 0; y < this.size; y++) {
				this.cells[x][y] = new Cell(new Coordinate(x, y));
			}
		}
		
		// set the mines
		for (int i = 0; i < this.totalMines; i++) {
			int x = (int) (Math.random() * (double)this.size);
			int y = (int) (Math.random() * (double)this.size);
			
			Cell cell = this.cells[x][y];
			
			// is the cell currently a mine?
			if (cell.isMine()) {
				i--;
				continue;
			}
			
			cell.setMine();
			
			this.mines[i] = cell;
			
			// increment the nearby mines for each cell around the current one
			for (int newX = Math.max(0, x-1); newX <= x + 1 && newX < this.size; newX++) {
				for (int newY = Math.max(0,  y - 1); newY <= y + 1 && newY < this.size; newY++) {
					if (newX == x && newY == y) continue;
					Cell nearbyCell = this.cells[newX][newY];
					nearbyCell.addNearbyMine();
				}
			}
		}
	}
	
	public void draw() {

		System.out.print("\n\n\n");
		System.out.print("   | ");
		for (int x = 0; x < this.size; x++) {
			System.out.print((x + 1) + " ");
		}
		System.out.print("\n---|");
		for (int x = 0; x < this.size; x++) {
			System.out.print("--");
		}
		System.out.print("-\n");
		for (int y = 0; y < this.size; y++) {
			System.out.printf("%2d | ", (y + 1));
			for (int x = 0; x < this.size; x++) {
				System.out.print(this.cells[x][y].display() + " ");
			}
			System.out.println();
		}		
		System.out.print("\n");
	}
	
	public void input(Coordinate location) {
		if (this.gameOver) {
			return;
		}
		
		Cell cell = this.cells[location.x - 1][location.y - 1];
		
		if (cell.isMine()) {
			
			System.out.println("Boom!");
			this.gameOver = true;
			
		} else {
			this.reveal(cell);
			if (this.totalRevealed == this.totalCells - this.totalMines) {
				System.out.println("Winner!");
				this.gameOver = true;
			}
		}
		
		if (this.gameOver) {
			for (int i = 0; i < this.totalMines; i++) {
				this.mines[i].reveal();
			}
		}
	}
	
	void reveal(Cell cell) {
		cell.reveal();
		this.totalRevealed++;
				
		if (cell.minesNearby > 0) return;
		
		int x = cell.location.x;
		int y = cell.location.y;
		
		for (int newX = Math.max(0, x-1); newX <= x + 1 && newX < this.size; newX++) {
			for (int newY = Math.max(0,  y - 1); newY <= y + 1 && newY < this.size; newY++) {
				if (newX == x && newY == y) continue;
				Cell nearbyCell = this.cells[newX][newY];
				if (nearbyCell.hidden) this.reveal(nearbyCell);
			}
		}
	}
}
