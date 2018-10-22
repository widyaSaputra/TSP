import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Development {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String lokasi [][] = null;
		String baris_awal[];
		long banyak_baris=1;
		ArrayList<String> perjalanan = new ArrayList<>();

		try {
			
			baris_awal = br.readLine().split(" ");
			int jumlah_lokasi = Integer.parseInt(baris_awal[0]);
//			lokasi= new String [jumlah_lokasi][2];
//			for(int i=0;i<jumlah_lokasi;i++) {
//				for(int j=0;j<2;j++) {
//					lokasi[i][j]=br.readLine();
//				}
//			}
			System.out.println(jumlah_lokasi);
			for(int k=1;k<jumlah_lokasi+1;k++) {
				banyak_baris= banyak_baris*k;
				System.out.println(banyak_baris);
			}
			System.out.println(banyak_baris);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
