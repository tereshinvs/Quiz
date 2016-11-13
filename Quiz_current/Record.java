public class Record implements Comparable<Record>
	{
		public String name;
		public int percent;

		Record(String temp_name, int temp_percent)
			{
				name = temp_name;
				percent = temp_percent;
			}

		public int compareTo(Record temp)
			{
				if (percent < temp.percent) return -1;
				if (percent > temp.percent) return 1;
				return name.compareTo(temp.name);
			}

		public boolean equals(Object obj)
			{
				if (getClass() != obj.getClass()) return false;
				return (name == ((Record)obj).name) && (percent == ((Record)obj).percent);
			}
	}