import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameInterfaceManager
	{
		private JButton start_button, ok_button, new_game_button;
		private JTextField question_amount_textfield, gamer_name_textfield;
		private JProgressBar progress_bar;
		private JRadioButton answers[];
		private ButtonGroup answers_group;
		private JLabel picture, result_label;
		private JCheckButton theme_check[];

		private GameManager game_manager;
		private JPanel game_panel;

		//Constructor
		GameInterfaceManager(JPanel temp_game_panel)
			{
				game_panel = temp_game_panel;
				game_panel.setLayout(null);

				//Textfield to input amount of questions
				System.out.println("Q_LEFT = " + Parameters.QUESTION_AMOUNT_TEXTFIELD_LEFT);
				System.out.println("Q_TOP = " + Parameters.QUESTION_AMOUNT_TEXTFIELD_TOP);
				question_amount_textfield = new JTextField("Input amount of question here...");
				question_amount_textfield.setBounds(Parameters.QUESTION_AMOUNT_TEXTFIELD_LEFT, Parameters.QUESTION_AMOUNT_TEXTFIELD_TOP,
																Parameters.QUESTION_AMOUNT_TEXTFIELD_WIDTH, Parameters.QUESTION_AMOUNT_TEXTFIELD_HEIGHT);
				game_panel.add(question_amount_textfield);

				gamer_name_textfield = new JTextField("Input your name here...");
				System.out.println("GAMER_NAME_LEFT = " + Parameters.GAMER_NAME_TEXTFIELD_LEFT);
				System.out.println("GAMER_NAME_TOP = " + Parameters.GAMER_NAME_TEXTFIELD_TOP);
				gamer_name_textfield.setBounds(Parameters.GAMER_NAME_TEXTFIELD_LEFT, Parameters.GAMER_NAME_TEXTFIELD_TOP,
														Parameters.GAMER_NAME_TEXTFIELD_WIDTH, Parameters.GAMER_NAME_TEXTFIELD_HEIGHT);
				game_panel.add(gamer_name_textfield);

				//Initialization buttons, textfields etc.
				start_button = new JButton("Start");
				start_button.setBounds(Parameters.START_BUTTON_LEFT, Parameters.START_BUTTON_TOP,
												Parameters.START_BUTTON_WIDTH, Parameters.START_BUTTON_HEIGHT);
				game_panel.add(start_button);

				ok_button = new JButton("OK");
				ok_button.setBounds(Parameters.OK_BUTTON_LEFT, Parameters.OK_BUTTON_TOP, Parameters.OK_BUTTON_WIDTH, Parameters.OK_BUTTON_HEIGHT);
				game_panel.add(ok_button);

				new_game_button = new JButton("New game");
				new_game_button.setBounds(Parameters.NEW_GAME_BUTTON_LEFT, Parameters.NEW_GAME_BUTTON_TOP,
													Parameters.NEW_GAME_BUTTON_WIDTH, Parameters.NEW_GAME_BUTTON_HEIGHT);
				game_panel.add(new_game_button);

				//Progress bar to show how many questions were
				progress_bar = new JProgressBar(JProgressBar.HORIZONTAL);
				progress_bar.setBounds(Parameters.PROGRESS_BAR_LEFT, Parameters.PROGRESS_BAR_TOP,
												Parameters.PROGRESS_BAR_WIDTH, Parameters.PROGRESS_BAR_HEIGHT);
				progress_bar.setValue(0);
				progress_bar.setStringPainted(true);
				game_panel.add(progress_bar);

				//Answer case radiobuttons
				answers_group = new ButtonGroup();
				answers = new JRadioButton[Parameters.ANSWER_CASE_AMOUNT];
				for (int i = 0; i < Parameters.ANSWER_CASE_AMOUNT; i++)
					{
						System.out.println("i = " + i + " "+Parameters.ANSWER_CASE_LEFT + i*Parameters.ANSWER_CASE_WIDTH);
						answers[i] = new JRadioButton();
						answers[i].setBounds(Parameters.ANSWER_CASE_LEFT + i*Parameters.ANSWER_CASE_WIDTH, Parameters.ANSWER_CASE_TOP,
													Parameters.ANSWER_CASE_WIDTH, Parameters.ANSWER_CASE_HEIGHT);
						answers_group.add(answers[i]);
						game_panel.add(answers[i]);
					}

				//Car photo
				picture = new JLabel();
				picture.setBounds(Parameters.CAR_PHOTO_LEFT, Parameters.CAR_PHOTO_TOP, Parameters.CAR_PHOTO_WIDTH, Parameters.CAR_PHOTO_HEIGHT);
				picture.setHorizontalAlignment(JLabel.CENTER);
				picture.setVerticalAlignment(JLabel.CENTER);
				game_panel.add(picture);

				//Results:
				result_label = new JLabel();
				result_label.setBounds(Parameters.RESULT_LABEL_LEFT, Parameters.RESULT_LABEL_TOP,
				                       Parameters.RESULT_LABEL_WIDTH, Parameters.RESULT_LABEL_HEIGHT);
				result_label.setFont(Parameters.RESULT_LABEL_FONT);
				result_label.setHorizontalAlignment(JLabel.CENTER);
				result_label.setVerticalAlignment(JLabel.CENTER);
				game_panel.add(result_label);

				//choose themes:
				theme_check = new JCheckButton[Parameters.THEMES_AMOUNT];
				for (int i = 0; i < Parameters.THEMES_AMOUNT; i++)
					{
						theme_check[i] = new JCheckButton(Parameters.THEMES_NAMES[i]);
						theme_check[i].setBounds(Parameters.THEME_CHECK_LEFT, Parameters.THEME_CHECK_TOP + i*Parameters.THEME_CHECK_HEIGHT,
														Parameters.THEME_CHECK_WIDTH, Parameters.THEME_CHECK_HEIGHT);
						game_panel.add(theme_check[i]);
					}

				showStartScreen();
			}

		public void setGameManager(GameManager temp_game_manager)
			{
				game_manager = temp_game_manager;
				start_button.addActionListener(new StartButtonListener(this, question_amount_textfield, gamer_name_textfield, theme_check, game_manager));
				ok_button.addActionListener(new OKButtonListener(answers, game_manager));
				new_game_button.addActionListener(new NewGameButtonListener(this, game_manager));
			}

		//Methods to show panel with start button etc and hide it:
		public void showStartScreen()
			{
				ok_button.setVisible(false);
				start_button.setVisible(true);
				progress_bar.setVisible(false);
				picture.setVisible(false);
				result_label.setVisible(false);
				new_game_button.setVisible(false);

				question_amount_textfield.setVisible(true);
				question_amount_textfield.setText("Input amount of question here...");

				gamer_name_textfield.setVisible(true);
				gamer_name_textfield.setText("Input your name here...");

				for (int i = 0; i < Parameters.ANSWER_CASE_AMOUNT; i++)
					answers[i].setVisible(false);

				for (int i = 0; i < Parameters.THEMES_AMOUNT; i++)
					theme_check[i].setVisible(true);
			}

		public void hideStartScreen()
			{
				ok_button.setVisible(true);
				start_button.setVisible(false);
				progress_bar.setVisible(true);
				picture.setVisible(true);
				result_label.setVisible(false);
				new_game_button.setVisible(true);
				
				question_amount_textfield.setVisible(false);
				gamer_name_textfield.setVisible(false);

				for (int i = 0; i < Parameters.ANSWER_CASE_AMOUNT; i++)
					answers[i].setVisible(true);

				for (int i = 0; i < Parameters.THEMES_AMOUNT; i++)
					theme_check[i].setVisible(false);
			}


		public void showQuestion(String ans_case[], int theme_number)
			{
//				System.out.println("questioned = " + questioned);
//				System.out.println("question_amount = " + question_amount);
				progress_bar.setValue((int)Math.round(100*game_manager.getQuestioned() / (double)game_manager.getQuestionAmount()));
				progress_bar.setString(game_manager.getQuestioned() + "/" + game_manager.getQuestionAmount());

				for (int i = 0; i < Parameters.ANSWER_CASE_AMOUNT; i++)
					answers[i].setText(ans_case[i]);
				//Set radiobuttons unselected (hack from internet, I don't how it works):
				ButtonModel selection = answers_group.getSelection(); //!!!!!!!!!!!!!!!!!!!!!!!!!!!
				if (selection != null)											//!!!!!!!!!!!!!!!!!!!!!!!!!!!
					{																	//!!!!!!!!!!!!!!!!!!!!!!!!!!!
						selection.setGroup(null);								//!!!!!!!!!!!!!!!!!!!!!!!!!!!
						selection.setSelected(false);							//!!!!!!!!!!!!!!!!!!!!!!!!!!!
						selection.setGroup(answers_group);					//!!!!!!!!!!!!!!!!!!!!!!!!!!!
					}

				picture.setIcon(new ImageIcon(Parameters.ITEM_PHOTO_PATH[theme_number] + (game_manager.getRightCarNumber()+1) + ".jpg"));
			}

		//At the ending we must show results:
		public void showResults()
			{
				ok_button.setVisible(false);
				start_button.setVisible(false);
				progress_bar.setVisible(false);
				picture.setVisible(false);
				
				question_amount_textfield.setVisible(false);

				for (int i = 0; i < Parameters.ANSWER_CASE_AMOUNT; i++)
					answers[i].setVisible(false);

				result_label.setText("Right answers: " + game_manager.getRightAnswers() + "/" + game_manager.getQuestionAmount());
				result_label.setVisible(true);
			}

	}