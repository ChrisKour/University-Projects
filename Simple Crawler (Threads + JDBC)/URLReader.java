import java.net.*;
import java.io.*;
import java.sql.*;

public class URLReader {
	private static int currentRecord = 0;
	public static DB db = new DB();

	public static void main(String[] args) throws Exception { //ekkinhsh thread
		ReaderThread[] rthread = new ReaderThread[4];
		for (int i = 0; i < rthread.length; i++) {
			rthread[i] = new ReaderThread();
			rthread[i].start();
		}
	}
	
	private static synchronized int getRecord(){ //katanomh grammwn pinaka
		currentRecord++;
		String query = "select RecordID from Record where RecordID = "+ currentRecord; //oso yparxoyn grammes toy pinaka
		ResultSet rs;
		try {
			rs = db.runSql(query);
			if(rs.next())
				return currentRecord; //tis stelnoyme gia diabasma
			else
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	private static class ReaderThread extends Thread {

		public ReaderThread() {
		}

		public void run() {
			DB db = new DB();
			int record;
			while ((record = getRecord()) > 0) { //oso yparxoyn grammes ston pinaka
				String query = "select URL from Record where RecordID = "+ record;
				ResultSet rs;
				URL url = null;
				String temp = null;
				try {
					rs = db.runSql(query);
					if(rs.next())
						temp = rs.getString("URL"); //apo8hkeysh url
					url = new URL(temp);
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
					String inputLine;

					try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(record+".html"), "utf-8"))) {
						while ((inputLine = in.readLine()) != null) {//apo8hkeyoyme ta periexomena twn istoselidwn me onoma to record id toys
							writer.write(inputLine);
							writer.write("\n");
						}
					}
					in.close();
					query = "delete from Record where RecordID = "+ record; //diagrafh apo ton pinaka
					Statement st = db.conn.createStatement();
					st.execute(query);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				 catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}