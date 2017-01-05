package data;

import java.time.LocalDateTime;

public class Rit extends Locatie 
{
	private LocalDateTime begin;
	
	private Sorteercentrale sorteercentrale;
	
	public Rit(LocalDateTime begin, Sorteercentrale sorteercentrale) {
		super();
		this.begin = begin;
		this.sorteercentrale = sorteercentrale;
	}
	
	public LocalDateTime getBegin() {
		return begin;
	}

	public Sorteercentrale getSorteercentrale() {
		return sorteercentrale;
	}

	public void setBegin(LocalDateTime begin) {
		this.begin = begin;
	}

	public void setSorteercentrale(Sorteercentrale sorteercentrale) {
		this.sorteercentrale = sorteercentrale;
	}

	@Override
	public String getOmschrijving() {
		return "Uw pakket is verstuurd vanaf " + sorteercentrale + "en onderweg";
	}
}
