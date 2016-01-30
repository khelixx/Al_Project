package laby.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import gameframework.base.ObservableValue;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevel;
import gameframework.game.GameLevelDefaultImpl;

public class LabyGame implements Game, Observer  {

	protected CanvasDefaultImpl defaultCanvas = null;
	protected ObservableValue<Integer> score[] = new ObservableValue[1];
	protected ObservableValue<Integer> life[] = new ObservableValue[1];
	protected ObservableValue<Boolean> endOfGame = null;
	
	private Frame f;

	private GameLevelDefaultImpl currentPlayedLevel = null;
	protected int levelNumber;

	protected ArrayList<GameLevel> gameLevels;

	protected Label lifeText, scoreText;
	protected Label information;
	protected Label informationValue;
	protected Label lifeValue, scoreValue;
	protected Label currentLevel;
	protected Label currentLevelValue;

	public LabyGame() {
		life[0] = new ObservableValue<Integer>(0);
        score[0] = new ObservableValue<Integer>(0);
		
		lifeText = new Label("Lives:");
		scoreText = new Label("Score:");
		information = new Label("State:");
		informationValue = new Label("Playing");
		currentLevel = new Label("Level:");
		createGUI();
	}

	@Override
	public void createGUI() {
		// TODO Auto-generated method stub
		f = new Frame("Default Game");
		f.dispose();

		createMenuBar();
		Container c = createStatusBar();

		defaultCanvas = new CanvasDefaultImpl();
		defaultCanvas.setSize(40 * 47, 40 * 23);
		f.add(defaultCanvas);
		f.add(c, BorderLayout.NORTH);
		f.pack();
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("file");
		MenuItem start = new MenuItem("new game");
		MenuItem save = new MenuItem("save");
		MenuItem restore = new MenuItem("load");
		MenuItem quit = new MenuItem("quit");
		Menu game = new Menu("game");
		MenuItem pause = new MenuItem("pause");
		MenuItem resume = new MenuItem("resume");
		menuBar.add(file);
		menuBar.add(game);
		f.setMenuBar(menuBar);

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				start();
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		restore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restore();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resume();
			}
		});

		file.add(start);
		file.add(save);
		file.add(restore);
		file.add(quit);
		game.add(pause);
		game.add(resume);
	}
	
	private Container createStatusBar() {
		JPanel c = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		c.setLayout(layout);
		lifeValue = new Label(Integer.toString(life[0].getValue()));
		scoreValue = new Label(Integer.toString(score[0].getValue()));
		currentLevelValue = new Label(Integer.toString(levelNumber));
		c.add(lifeText);
		c.add(lifeValue);
		c.add(scoreText);
		c.add(scoreValue);
		c.add(currentLevel);
		c.add(currentLevelValue);
		c.add(information);
		c.add(informationValue);
		return c;
	}

	public Canvas getCanvas() {
		return defaultCanvas;
	}

	public void start() {
		score[0].addObserver(this);
		life[0].addObserver(this);
		life[0].setValue(3);
		score[0].setValue(0);
		levelNumber = 0;
		for (GameLevel level : gameLevels) {
			endOfGame = new ObservableValue<Boolean>(false);
			endOfGame.addObserver(this);
			try {
				if (currentPlayedLevel != null && currentPlayedLevel.isAlive()) {
					currentPlayedLevel.interrupt();
					currentPlayedLevel = null;
				}
				currentPlayedLevel = (GameLevelDefaultImpl) level;
				levelNumber++;
				currentLevelValue.setText(Integer.toString(levelNumber));
				currentPlayedLevel.start();
				currentPlayedLevel.join();
			} catch (Exception e) {
			}
		}

	}
	public void restore() {
		System.out.println("restore(): Unimplemented operation");
	}

	public void save() {
		System.out.println("save(): Unimplemented operation");
	}

	public void pause() {
		currentPlayedLevel.interrupt();
	}

	public void resume() {
		currentPlayedLevel.run();
	}

	public ObservableValue<Integer>[] score() {
		return score;
	}

	public ObservableValue<Integer>[] life() {
		return life;
	}

	public ObservableValue<Boolean> endOfGame() {
		return endOfGame;
	}

	public void setLevels(ArrayList<GameLevel> levels) {
		gameLevels = levels;
	}

	public void update(Observable o, Object arg) {
		if (o == endOfGame) {
			if (endOfGame.getValue()) {
				informationValue.setText("You win");
				currentPlayedLevel.interrupt();
				currentPlayedLevel.end();
			}
		} else {
			for (ObservableValue<Integer> lifeObservable : life) {
				if (o == lifeObservable) {
					int lives = ((ObservableValue<Integer>) o).getValue();
					lifeValue.setText(Integer.toString(lives));
					if (lives == 0) {
						informationValue.setText("Defeat");
						currentPlayedLevel.interrupt();
						currentPlayedLevel.end();
					}
				}
			}
			for (ObservableValue<Integer> scoreObservable : score) {
				if (o == scoreObservable) {
					scoreValue
							.setText(Integer
									.toString(((ObservableValue<Integer>) o)
											.getValue()));
				}
			}
		}
	}

}
