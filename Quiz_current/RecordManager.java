import java.util.*;
import java.io.*;

public class RecordManager
	{
		private RecordInterfaceManager record_interface_manager;

		private int record_amount;
		private Record records[];

		public void setRecordInterfaceManager(RecordInterfaceManager temp_record_interface_manager)
			{
				record_interface_manager = temp_record_interface_manager;
			}

		public void loadRecords(boolean need_update_interface)
			{
				Scanner in = new Scanner(System.in); //Hack

				try
					{
						in = new Scanner(new File(Parameters.RECORDS_FILE_PATH));
					}
				catch (IOException e) //If there is no record file
					{
						System.out.println("File not found: " + Parameters.RECORDS_FILE_PATH);
						System.exit(0);
					}

				record_amount = in.nextInt(); in.nextLine();
				records = new Record[Parameters.RECORDS_LINE_AMOUNT + 1];
				for (int i = 0; i < record_amount; i++)
					{
						records[i] = new Record(in.nextLine(), in.nextInt());
						in.nextLine();
					}
				in.close();

				if (need_update_interface) record_interface_manager.updateRecordInterface(records, record_amount);
			}

		public void checkOnRecord(String gamer_name, int temp_percent)
			{
				//check if it's a record
				loadRecords(Parameters.WITHOUT_UPDATING_INTERFACE);
				records[record_amount++] = new Record(gamer_name, temp_percent);
				System.out.println("record_amount = " + record_amount);
				Arrays.sort(records, 0, record_amount);

				//Write record to file:
				PrintWriter out = new PrintWriter(System.out);
				try
					{
						out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Parameters.RECORDS_FILE_PATH)));
					}
				catch (IOException e)
					{
						System.out.println("File not found: " + Parameters.RECORDS_FILE_PATH);
						System.exit(0);
					}
				out.println(Math.min(record_amount, Parameters.RECORDS_LINE_AMOUNT));
				for (int i = Math.min(record_amount, Parameters.RECORDS_LINE_AMOUNT+1)-1;
						i >= (record_amount <= Parameters.RECORDS_LINE_AMOUNT ? 0 : 1); i--)
					{
						out.println(records[i].name);
						out.println(records[i].percent);
					}
				out.flush();
				out.close();
			}


		public void deleteRecords()
			{
				PrintWriter out = new PrintWriter(System.out);
				try
					{
						out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Parameters.RECORDS_FILE_PATH)));
					}
				catch (IOException e)
					{
						System.out.println("File not found: " + Parameters.RECORDS_FILE_PATH);
						System.exit(0);
					}
				out.println(0);
				out.flush();
				out.close();
			}

	}