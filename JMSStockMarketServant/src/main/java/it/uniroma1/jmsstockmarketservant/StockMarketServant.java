package it.uniroma1.jmsstockmarketservant;

public class StockMarketServant {

	public static void main(String args[]) throws Exception {

	NotificatoreAcquisto n = new NotificatoreAcquisto();
                n.start();	
            
            ProduttoreQuotazioni q = new ProduttoreQuotazioni();
		q.start();
                
                
	}
}
