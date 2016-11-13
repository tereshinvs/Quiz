import javax.swing.*;
import java.awt.event.*;

public class OKButtonListener implements ActionListener
	{
		//ActionListener for ok button
		private JRadioButton answers[];
		private GameManager game_manager;

		OKButtonListener(JRadioButton temp_answers[], GameManager temp_game_manager)
			{
				answers = temp_answers;
				game_manager = temp_game_manager;
			}

		//Event on click
		public void actionPerformed(ActionEvent aevent)
			{
				//Checking answer
				for (int i = 0; i < Parameters.ANSWER_CASE_AMOUNT; i++)
					if (answers[i].isSelected()) game_manager.checkAnswer(i);
					if (game_manager.getQuestioned() < game_manager.getQuestionAmount()) game_manager.generateQuestion();
																								else game_manager.endQuiz();
			}
	}
