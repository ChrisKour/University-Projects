//
//Download JSoup core library from http://jsoup.org/download.
//Download mysql-connector-java-xxxbin.jar from http://dev.mysql.com/downloads/connector/j/
//
//CREATE TABLE IF NOT EXISTS `Record` (
//  `RecordID` INT(11) NOT NULL AUTO_INCREMENT,
//  `URL` text NOT NULL,
//  PRIMARY KEY (`RecordID`)
//) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
//

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SimpleCrawlerDB {
	public static DB db = new DB();
	public static int j = 1;
	private static int currentRecord = 0;
	private static int level = 0;

	public static void main(String[] args) throws SQLException, IOException {

		Statement st = db.conn.createStatement();
		db.runSql2("TRUNCATE Record;"); //diagrafh olou tou pinaka
		String query = "INSERT INTO Record (RecordID,URL,Level) VALUES (1,'http://www.pcsteps.com/',1)"; //1h timh toy pinaka
		st.execute(query);
		
		CrawlerThread[] cthread = new CrawlerThread[4];	//ekinhsh thread
		for (int i = 0; i < cthread.length; i++) {
			cthread[i] = new CrawlerThread();
			cthread[i].start();
		}
	}

	private static synchronized int getRecord() { //katanomh grammwn pinaka oso einai mikrotes enos epipedou
		level = 0;
		currentRecord++;
		String query = "select Level from Record where RecordID = "+ currentRecord;
		ResultSet rs; //elegxos tou epipedoy twn eggrafwn tou pinaka
		try {
			rs = db.runSql(query);
			while (rs.next())
				level = rs.getInt("Level");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (level < 3 && level != 0) { //an einai mikrotero aytou poy exoume 8esei san orio to stelnoume
			return currentRecord;
		} else 
			return -1;
	}
	
	private static synchronized void updateTable(String url,int mylevel) throws SQLException { //elegxos diplotypiwn kai eggrafh ston pinaka
		Statement st = db.conn.createStatement();
		String query = "select * from Record where URL = '"+ url + "'"; //elegxos an yparxei hdh to url ston pinaka
		ResultSet rs = db.runSql(query);
		if (rs.next()) {
		} else {//an den yparxei to pername
			query = "INSERT INTO Record (URL,Level) VALUES ('"+ url + "'," + mylevel+ ")";
			st.execute(query);
		}
	
	}

	private static class CrawlerThread extends Thread {

		public CrawlerThread() {
		}

		public void run() {
			DB db = new DB();
			String URL = "", query;
			ResultSet rs;
			if(Thread.currentThread().getId()%4!=0) //mia mikrh ka8ysterhsh wste na exei na dwsei grammes o pinakas
				try {
					sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			try {
				int currlevel=0;
				int record;
				while((record = getRecord()) > 0){ //oso yparxoun katalhles eggrafes ston pinaka psaxnoyme gia links-paidia
					System.out.println(Thread.currentThread().getId()%4+","+record);
					query = "select URL from Record where RecordID = "+ record; //pairnoyme to url
					rs = db.runSql(query);
					while (rs.next())
						URL = rs.getString("URL");
					System.out.println(Thread.currentThread().getId()%4+","+URL);
					query = "select Level from Record where URL = '" + URL + "'"; //to epipedo
					rs = db.runSql(query);
					while (rs.next())
						currlevel = rs.getInt("Level");

					Document doc = Jsoup.connect(URL).get(); //me to jsoup psaxnoume gia links poy na deixnoyn sto idio to site
					Elements questions = doc.select("a[href]");
					currlevel++;
					for (Element link : questions) {
						if (link.attr("href").contains("pcsteps.com")) {
							updateTable(link.attr("abs:href"),currlevel); //ta pairname sthn bash
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}