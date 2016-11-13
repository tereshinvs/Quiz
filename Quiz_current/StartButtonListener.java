import javax.swing.*;
import java.awt.event.*;

public class StartButtonListener implements ActionListener
	{
		//ActionListener for start button
		private GameInterfaceManager game_interface_manager;
		private JTextField question_amount_textfield, gamer_name_textfield;
		private JCheckButton theme_check[];
		private GameManager game_manager;

		StartButtonListener(GameInterfaceManager temp_game_interface_manager,
									JTextField temp_question_amount_textfield,
									JTextField temp_gamer_name_textfield,
									JCheckButton temp_theme_check[],
									GameManager temp_game_manager)
			{
				game_interface_manager = temp_game_interface_manager;
				question_amount_textfield = temp_question_amount_textfield;
				gamer_name_textfield = temp_gamer_name_textfield;
				theme_check = temp_theme_check;
				game_manager = temp_game_manager;
			}

		public void actionPerformed(ActionEvent aevent)
			{
				//Getting question amount from question_amount_textfield
				System.out.println("qamount = " + question_amount_textfield.getText());
				System.out.println("gname = \"" + gamer_name_textfield.getText() + "\"");
				if (game_manager == null) System.out.println("fail");
				try
					{
						if (Integer.parseInt(question_amount_textfield.getText()) > game_manager.getCarAmount())
							{
								game_interface_manager.showStartScreen();
								System.out.println("too many question");
								return;
							}
						if (Integer.parseInt(question_amount_textfield.getText()) <= 0)
							{
								game_interface_manager.showStartScreen();
								System.out.println("too less question");
								return;
							}
						game_manager.setQuestionAmount(Integer.parseInt(question_amount_textfield.getText()));
						game_manager.setGamerName(
							gamer_name_textfield.getText().equals("Input your name here...") ?
							Parameters.DEFAULT_PLAYER_NAME : gamer_name_textfield.getText() );
					}
				catch (Exception e)
					{
						game_interface_manager.showStartScreen();
						System.out.println("NaN");
						return;
					}
				game_interface_manager.hideStartScreen();
				game_manager.generateQuestion();
			}
	}