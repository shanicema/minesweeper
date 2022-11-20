package minesweepergame;


// mine == *
// empty space = [ ]
// number = 1 - 8
// flags- no flags
// unclicked cells = ?

public class Cell {
	
	enum CellType {
		SPACE,
		MINE
	}
	
	
	public CellType type;
	public Coordinate location;
	public boolean hidden = true;
	public int minesNearby;
	
	public Cell(Coordinate location) {
		this.location = location;
		this.type =	CellType.SPACE;
	}
	
	public boolean isMine() {
		return this.type == CellType.MINE;
	}
	
	public void setMine() {
		this.type = CellType.MINE;
	}
	
	public void reveal() {
		this.hidden = false;
	}

	public void addNearbyMine() {
		this.minesNearby++;	
	}
	
	public char display() {
		if (!this.hidden) {
			switch (this.type) {
			case SPACE:
				return this.minesNearby > 0 ? Integer.toString(this.minesNearby).charAt(0) : ' ';
			case MINE:
				return '*';
			}
		}
		
		return '▢';
	}
}
