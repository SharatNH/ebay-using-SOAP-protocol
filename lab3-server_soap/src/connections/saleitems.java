package connections;



public class saleitems {
	
	int id;
	String user;
	String title;
	String itemdesc;
	String sellerinfo;
	
	int itemprice;
	int quantity;
	boolean bidding;
	int timecopy;

	
	
	public saleitems(int tid,String ttitle,String titemdesc,String tsellerinfo,int titemprice,int tquantity,boolean tbidding,int ttimecopy,String tuser)
	{
		
		
		user=tuser;
		title=ttitle;
		itemdesc=titemdesc;
		sellerinfo=tsellerinfo;
		itemprice=titemprice;
		quantity=tquantity;
		 bidding=tbidding;
		timecopy=ttimecopy;
		id=tid;
	}
	
}
