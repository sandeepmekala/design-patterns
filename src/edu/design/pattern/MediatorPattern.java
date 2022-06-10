package edu.design.pattern;

import java.util.ArrayList;
   
// It is used to handle communication between related objects(Colleagues)
// Allows loose coupling by encaptulating the way disperates sets of objects interact with each other. Allows for the actions of each object set to vary independently of one another.
public class MediatorPattern {
	public static void main(String[] args) {
		StockMediator nyse = new StockMediator();
		Gorman broker1 = new Gorman(nyse);
		JTMorman broker2 = new JTMorman(nyse);
		
		broker1.saleOffer("MSFT", 100);
		broker1.saleOffer("GOOG", 50);
		
		broker2.buyOffer("MSFT", 100);
		broker2.saleOffer("NRG", 10);
		
		broker1.buyOffer("NRG", 10);
		
		nyse.getAllStockOffers();
	}
}
class StockOffer{
	private int stockShare = 0;
	private String stockSymbal = "";
	private int colleagueCode = 0;
	public StockOffer(int stockShare, String stockSymbal, int colleagueCode) {
		this.stockShare = stockShare;
		this.stockSymbal = stockSymbal;
		this.colleagueCode = colleagueCode;
	}
	public int getStockShare() {
		return stockShare;
	}
	public String getStockSymbal() {
		return stockSymbal;
	}
	public int getColleagueCode() {
		return colleagueCode;
	}
}
abstract class Colleague{
	private Mediator mediator;
	private int colleagueCode;
	
	public Colleague(Mediator mediator){
		this.mediator = mediator;
	}
	public void setColleagueCode(int colleagueCode){
		this.colleagueCode = colleagueCode;
	}
	public void saleOffer(String stock, int shares){
		mediator.saleOffer(stock, shares, this.colleagueCode);
	}
	public void buyOffer(String stock, int shares){
		mediator.buyOffer(stock, shares, this.colleagueCode);
	}
}
class Gorman extends Colleague{
	public Gorman(Mediator mediator) {
		super(mediator);
		System.out.println("Gorman singed up");
	}
}
class JTMorman extends Colleague{
	public JTMorman(Mediator mediator) {
		super(mediator);
		System.out.println("JTMorman singed up");
	}
}
interface Mediator{
	void saleOffer(String stock, int shares, int colleagueCode);
	void buyOffer(String stock, int shares, int colleagueCode);
	void addColleague(Colleague colleague);
}
class StockMediator implements Mediator{
	private ArrayList<Colleague> colleagues = new ArrayList<Colleague>(); 
	private ArrayList<StockOffer> stockBuyOffers = new ArrayList<StockOffer>(); 
	private ArrayList<StockOffer> stockSaleOffers = new ArrayList<StockOffer>(); 
	private int colleagueCode = 0;

	public void addColleague(Colleague colleague) {
		colleague.setColleagueCode(colleagueCode++);
		colleagues.add(colleague);
	}
	public void saleOffer(String stock, int shares, int colleagueCode) {
		boolean stockSold = false;
		for(StockOffer offer : stockBuyOffers){
			if(offer.getStockSymbal().equals(stock) && offer.getStockShare() == shares){
				System.out.println(shares+" of "+stock+"sold to "+offer.getColleagueCode());
				stockBuyOffers.remove(offer);
				stockSold = true;
				break;
			}
		}
		if(!stockSold){
			System.out.println(shares+" of "+stock+" added to inventory");
			stockSaleOffers.add(new StockOffer(shares, stock, colleagueCode));
		}
	}
	public void buyOffer(String stock, int shares, int colleagueCode) {
		boolean stockBought = false;
		for(StockOffer offer : stockSaleOffers){
			if(offer.getStockSymbal().equals(stock) && offer.getStockShare() == shares){
				System.out.println(shares+" of "+stock+"bought by "+offer.getColleagueCode());
				stockSaleOffers.remove(offer);
				stockBought = true;
				break;
			}
		}
		if(!stockBought){
			System.out.println(shares+" of "+stock+" added to inventory");
			stockBuyOffers.add(new StockOffer(shares, stock, colleagueCode));
		}
	}
	public void getAllStockOffers(){
		System.out.println("Stocks for sale:");
		for(StockOffer offer : stockSaleOffers){
			System.out.println(offer.getStockShare()+" of "+ offer.getStockSymbal());
		}
		System.out.println("Stocks buy offers:");
		for(StockOffer offer : stockBuyOffers){
			System.out.println(offer.getStockShare()+" of "+ offer.getStockSymbal());
		}
	}
}
