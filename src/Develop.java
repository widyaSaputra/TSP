import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Develop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String lokasi_file = "F://TSP/real1.txt";
		String baris_pertama[];        
		String lokasi[][]=null;
		ArrayList<String> tabu = new ArrayList<>();
		ArrayList<String> list_penerbangan = new ArrayList<>();
		ArrayList<String> pemilihan = new ArrayList<>();
		ArrayList<String> fix = new ArrayList<>();
		int jml_biaya_total=0;
		String perjalanan_saat_ini=null;
		String perjalanan_selanjutnya=null;
		int suhu_awal = 100;
		String temp[]=new String[4];
		int jml_biaya_perhitungan = 0;
		float rata_biaya;
		boolean start_point=true;
		int penanda_masuk_tolak=0;
		
		
		        try {
		            FileReader fr = new FileReader(lokasi_file);
		            BufferedReader br = new BufferedReader(fr);
		            
		            String text;
		            
		            baris_pertama = br.readLine().split(" ");
		            int lokasi_bandara = Integer.parseInt(baris_pertama[0]);
		            lokasi = new String[lokasi_bandara][lokasi_bandara];
		            for(int i=0;i<lokasi_bandara;i++) {
		            	for(int j=0;j<2;j++) {
		            		//get lokasi dan bandara
		            		lokasi[i][j]=br.readLine();
		            	}
		            }
		            while ((text = br.readLine()) != null)
		            	list_penerbangan.add(text);

		            
		            perjalanan_saat_ini = baris_pertama[1];
		            int cari_list=0;
		            for(int day=1;day<lokasi_bandara+1;day++) {
		            	
		            	while(pemilihan.size()<1) {
			            		for(int l=0;l<list_penerbangan.size();l++) {
			            			temp=list_penerbangan.get(l).split(" ");
					            		if((temp[0].equals(perjalanan_saat_ini)&&Integer.parseInt(temp[2])==day) 
					            				||(temp[0].equals(perjalanan_saat_ini)&&Integer.parseInt(temp[2])==0)) {
					            			if(start_point) {
					            				pemilihan.add(list_penerbangan.get(l));
						            			jml_biaya_perhitungan = jml_biaya_perhitungan + Integer.parseInt(temp[3]);
					            			}else {
					            				if(fix.size()<lokasi_bandara-1)
					            				{
					            					for(int n=0;n<fix.size();n++) {
						            					String [] sementara= fix.get(n).split(" ");
						            					if(temp[1].equals(sementara[0])) {
						            						penanda_masuk_tolak=1;
						            						break;
						            					}
							            			}
					            					if(penanda_masuk_tolak==0) {
					            						for(int larangan=0;larangan<tabu.size();larangan++) {
					            							String isi_tabu[] = tabu.get(larangan).split(" ");

					            							if(isi_tabu.length>1) {
					            								if(perjalanan_saat_ini.equals(isi_tabu[0])||
					            										perjalanan_saat_ini.equals(isi_tabu[1])) {
					            									penanda_masuk_tolak=1;
					            									break;
					            								}
					            							}else { 
					            								if(perjalanan_saat_ini.equals(isi_tabu[0])) {
					            								penanda_masuk_tolak=1;
						            							break;
						            							}
						            						}
						            					}
					            					}
					            					if(penanda_masuk_tolak==0) {
						            					pemilihan.add(list_penerbangan.get(l));
								            			jml_biaya_perhitungan = jml_biaya_perhitungan + Integer.parseInt(temp[3]);
						            				}
					            				}
					            				else 
					            				{
					            					if(temp[0].equals(perjalanan_saat_ini)&&temp[1].equals(baris_pertama[1])) {
					            						pemilihan.add(list_penerbangan.get(l));
								            			jml_biaya_perhitungan = jml_biaya_perhitungan + Integer.parseInt(temp[3]);
					            					}
					            				}
					            			}			            		
					            		}
				            		penanda_masuk_tolak=0;
			            	}
			            		
			            		
			            		if(pemilihan.size()<1) {
			            			int akhir = fix.size()-1;
			            			String sementara[]=fix.get(akhir).split(" ");
//			            			System.out.println(sementara[0] + " " + sementara[1]);
			            			tabu.remove(tabu.size()-1);
//			            			tabu.add(sementara[0] + " " + sementara[1]);
			            			fix.remove(akhir);
			            			String bandara_baru[] =fix.get(akhir-1).split(" "); 
			            			perjalanan_saat_ini = bandara_baru[1];
				            	}
//			            		else {
//				            		cari_list=1;
//				            	}
			            		
		            	}	
		            	rata_biaya = (float) jml_biaya_perhitungan/pemilihan.size();
		            	boolean acak_nilai=true;
		            	while(acak_nilai) {
		            		int angka_acak = (int) (Math.random()*pemilihan.size());
		            		//sumber eror
		            		String [] penyimpanan_sementara=pemilihan.get(angka_acak).split(" ");
		            		int biaya = Integer.parseInt(penyimpanan_sementara[3]);
		            		if(biaya<rata_biaya) {
		            			suhu_awal=suhu_awal-1;
		            			perjalanan_saat_ini=penyimpanan_sementara[1];
		            			jml_biaya_total = jml_biaya_total+biaya;
		            			acak_nilai=false;
		            			perjalanan_saat_ini=penyimpanan_sementara[1];
		            			fix.add(penyimpanan_sementara[0]+" "+penyimpanan_sementara[1]+" "+day+" "+penyimpanan_sementara[3]);
//		            			System.out.println(penyimpanan_sementara[0]+" "+penyimpanan_sementara[1]);

		            			//nambah ke tabu
		            			for(int i=0;i<lokasi.length;i++) {
		            				String sementara[] = lokasi[i][1].split(" ");
		            				if(sementara.length>1) {
		            					if(sementara[0].equals(penyimpanan_sementara[0])||
			            						sementara[1].equals(penyimpanan_sementara[0])) {
			            					tabu.add(sementara[0] + " "+sementara[1]);
			            					break;
			            				}
		            				}else {
		            					if(sementara[0].equals(penyimpanan_sementara[0])) 
		            						tabu.add(penyimpanan_sementara[0]);
		            				}
		            					
		            			}
		            			
		            		}else if(biaya>rata_biaya&&suhu_awal>30) {
		            			suhu_awal=suhu_awal-1;
		            			perjalanan_saat_ini=penyimpanan_sementara[1];
		            			jml_biaya_total = jml_biaya_total+biaya;
		            			acak_nilai=false;
		            			perjalanan_saat_ini=penyimpanan_sementara[1];
		            			fix.add(penyimpanan_sementara[0]+" "+penyimpanan_sementara[1]+" "+day+" "+penyimpanan_sementara[3]);
//		            			System.out.println(penyimpanan_sementara[0]+" "+penyimpanan_sementara[1]);
		            			//nambah ke tabu
		            			for(int i=0;i<lokasi.length;i++) {
		            				String sementara[] = lokasi[i][1].split(" ");
		            				if(sementara.length>1) {
		            					if(sementara[0].equals(penyimpanan_sementara[0])||
			            						sementara[1].equals(penyimpanan_sementara[0])) {
			            					tabu.add(sementara[0] + " " +sementara[1]);
			            					break;
			            				}
		            				}else {
		            					if(sementara[0].equals(penyimpanan_sementara[0])) {
		            						tabu.add(penyimpanan_sementara[0]);

		            					}
		            						
		            				}
		            					
		            			}
		            		}
			            	
			            	
		            	}

		            	pemilihan.clear();
		            	start_point=false;
		            }
		            
		            System.out.println(jml_biaya_total);
		            for(int i =0;i<fix.size();i++) {
		            	System.out.println(fix.get(i));
		            }
//		            System.out.println();
//		            for(int j =0;j<tabu.size();j++) {
//		            	System.out.println(tabu.get(j));
//		            }
		            
		        } 
		        catch (FileNotFoundException fnfe) {
		            fnfe.getMessage();
		        } 
		        catch (IOException ioe) {
		            ioe.getMessage();
		        }
		
	}

}
