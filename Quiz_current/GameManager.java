import java.util.*;
import java.io.*;

public class GameManager
	{
		private int item_amount, question_amount, questioned = 0, right_answers = 0, alr_questioned[], right_item, right_item_place;
		private String item_names[], gamer_name;

		private GameInterfaceManager game_interface_manager;
		private RecordManager record_manager;

		//Constructor
		GameManager() { loadData(); }

		public int getQuestionAmount() { return question_amount; }
		public int getQuestioned() { return questioned; }
		public int getRightItemNumber() { return right_car; }
		public int getRightAnswers() { return right_answers; }
		public int getItemAmount() { return car_amount; }

		//Configurate this GameManager:
		public void setGameInterfaceManager(GameInterfaceManager temp_game_interface_manager)
			{
				game_interface_manager = temp_game_interface_manager;
			}

		public void setGamerName(String temp_name)
			{
				gamer_name = temp_name;
			}

		public void setRecordManager(RecordManager temp_record_manager)
			{
				record_manager = temp_record_manager;
			}

		public void setQuestionAmount(int temp_question_amount)
			{
				question_amount = temp_question_amount;
				alr_questioned = new int[question_amount];
			}

		//Load programm data (car names etc):
		private void loadData()
			{
				Scanner fin = new Scanner(System.in); //Hack

				//Load car names:
				try
					{
						fin = new Scanner(new File(Parameters.ITEM_NAMES_FILE_PATH));
					}
				catch (IOException e) //If programm was corrupted
					{
						System.out.println("File not found: " + Parameters.ITEM_NAMES_FILE_PATH[0]);
						System.exit(0);
					}

				while (true)
					{
						cur_line = in.nextLine();
						if (cur_line.equals("/end")) return;
					}

				item_amount = fin.nextInt(); fin.nextLine(); //To read Strings
				System.out.println("item_amount = " + item_amount);
				item_names = new String[item_amount];
				for (int i = 0; i < item_amount; i++)
					item_names[i] = fin.nextLine();
			}

		public void generateQuestion()
			{
				boolean flag;

				//Find unique next car:
				while (true)
					{
						right_car = new Random().nextInt(car_amount);
						flag = true;
						for (int i = 0; i < questioned; i++)
							if (alr_questioned[i] == right_car) flag = false;
						if (flag) break;
					}
				alr_questioned[questioned++] = right_car;

				//Generate answer case
				int ans_case[] = new int[Parameters.ANSWER_CASE_AMOUNT];
				for (int i = 0; i < Parameters.ANSWER_CASE_AMOUNT; i++)
					while (true)
						{
							ans_case[i] = new Random().nextInt(car_amount);
							if (ans_case[i] == right_car) continue;
							flag = true;
							for (int j = 0; j < i; j++)
								if (ans_case[j] == ans_case[i]) flag = false;
							if (flag) break;
						}

				//Generate right answer place:
				right_car_place = new Random().nextInt(Parameters.ANSWER_CASE_AMOUNT);
				ans_case[right_car_place] = right_car;

				String ans_names[] = new String[Parameters.ANSWER_CASE_AMOUNT];
				for (int i = 0; i < Parameters.ANSWER_CASE_AMOUNT; i++)
					ans_names[i] = car_names[ans_case[i]];

				game_interface_manager.showQuestion(ans_names);
			}

		//Checking answer method:
		public void checkAnswer(int ans_number)
			{
				if (ans_number == right_car_place) right_answers++;
			}

		//If questioned == question_amount
		public void endQuiz()
			{
				game_interface_manager.showResults();

				record_manager.checkOnRecord(gamer_name, Math.round(100*right_answers / question_amount));
			}

		//Method to initialize new game:
		public void newGame()
			{
				questioned = right_answers = 0;
			}

	}