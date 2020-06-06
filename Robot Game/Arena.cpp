#include "Globals.h"
#include "Arena.h"
#include "Robot.h"
#include "Player.h"
#include <iostream>

using namespace std;

Arena::Arena(int nRows, int nCols)
{
	if (nRows <= 0 || nCols <= 0 || nRows > MAXROWS || nCols > MAXCOLS)
	{
		cout << "***** Arena created with invalid size " << nRows << " by "
			<< nCols << "!" << endl;
		exit(1);
	}

	m_rows = nRows;
	m_cols = nCols;
	m_player = nullptr;
	m_nRobots = 0;
}

Arena::~Arena()
{
	// TODO:  Delete the player and all remaining dynamically allocated robots.
	delete m_player;
	for (int i = 0; i < m_nRobots; i++)
		delete m_robots[i];
}

int Arena::rows() const
{
	// TODO:  TRIVIAL:  Return the number of rows in the arena.
	return m_rows;
}

int Arena::cols() const
{
	// TODO:  TRIVIAL:  Return the number of columns in the arena.
	return m_cols;
}

Player* Arena::player() const
{
	return m_player;
}

int Arena::robotCount() const
{
	return m_nRobots;
}

int Arena::nRobotsAt(int r, int c) const
{
	int counter = 0;
	for (int i = 0; i != m_nRobots; i++)
	{
		if (r == m_robots[i]->row())
			if (c == m_robots[i]->col())
				counter++;
	}
	// TODO:  Return the number of robots at row r, column c.
	return counter;
}

void Arena::displayRules() const
{
	cout << "Welcome to the The Matrix! The Machines that created \n"
		<< "The Matrix are attacking Zion and Neo is nowhere to \n"
		<< "be found! Before they reach Zion you've been tasked \n"
		<< "with holding them off.\n" << endl;

	cout << "The rules of the game are simple; you are indicated \n"
		<< "by the '@' sign, while robots are 'R' (or a number to \n"
		<< "indicate the number of robots on that spot). Each robot \n"
		<< "has two lives--destroy them at all costs!\n" << endl;

	cout << "You can move left (l), right (r), up (u), or down (d). \n"
		<< "You also have the choice to shoot left (sl), shoot \n"
		<< "right (sr), shoot up (su), or shoot down (sd). And if \n" 
		<< "you have no idea what to do, you can let the computer \n" 
		<< "decide for you (c)! As a last resort, you can quit (q) \n"
		<< "to exit the game.\n" << endl;

	cout << "Press enter to continue: ";
	cin.ignore();
}

// FINISH THIS PORTION!!
void Arena::display(std::string msg) const
{
	// Position (row,col) in the arena coordinate system is represented in
	// the array element grid[row-1][col-1]
	char grid[MAXROWS][MAXCOLS];
	int r, c;

	// Fill the grid with dots
	for (r = 0; r < rows(); r++)
		for (c = 0; c < cols(); c++)
			grid[r][c] = '.';

	// Indicate each robot's position
  // TODO:  If one robot is at some grid point, set the char to 'R'.
  //        If it's 2 though 8, set it to '2' through '8'.
  //        For 9 or more, set it to '9'.

	//printing out a table of rules
	//displayRules();

	char temp;
	for (int r = 0; r < rows(); r++)
		for (int c = 0; c < cols(); c++)
		{
			temp = ' ';

			if (nRobotsAt(r + 1, c + 1) > 9)
				temp = '9';
			else if (nRobotsAt(r + 1, c + 1) > 1) //between the bounds
			{
				if (nRobotsAt(r + 1, c + 1) < 9)
					temp = '0' + nRobotsAt(r + 1, c + 1);
			}
			else if (nRobotsAt(r + 1, c + 1) == 1)
				temp = 'R';
			

			if (temp == ' ')
				;
			else
				grid[r][c] = temp;
		}


	// Indicate player's position
	if (m_player != nullptr)
	{
		// Set the char to '@', unless there's also a robot there,
		// in which case set it to '*'.
		char& gridChar = grid[m_player->row() - 1][m_player->col() - 1];
		if (gridChar == '.')
			gridChar = '@';
		else
			gridChar = '*';
	}

	// Draw the grid
	clearScreen();
	for (r = 0; r < rows(); r++)
	{
		for (c = 0; c < cols(); c++)
			cout << grid[r][c];
		cout << endl;
	}
	cout << endl;

	// Write message, robot, and player info
	cout << endl;
	if (msg != "")
		cout << msg << endl;
	cout << "There are " << robotCount() << " robots remaining." << endl;
	if (m_player == nullptr)
		cout << "There is no player." << endl;
	else
	{
		if (m_player->age() > 0)
			cout << "The player has lasted " << m_player->age() << " steps." << endl;
		if (m_player->isDead())
		{
			cout << "The player is dead." << endl;
			//cout << "\n--- YOU HAVE LOST THE GAME! BETTER LUCK NEXT TIME ---" << endl;
		}
	}
	//if (robotCount() == 0)
		//cout << "\n--- YOU HAVE WON THE GAME! CONGRATS ---" << endl;
}

bool Arena::addRobot(int r, int c)
{
	// If MAXROBOTS have already been added, return false.  Otherwise,
	// dynamically allocate a new robot at coordinates (r,c).  Save the
	// pointer to the newly allocated robot and return true.

	// TODO:  Implement this.
	// This implementation compiles, but is incorrect.
	bool status;
	if (m_nRobots == MAXROBOTS)
	{
		cout << "Invalid number of robots--the game has ended?" << endl;
		status = false;
	}
	else
	{
		//check the parameters.. similar to addPlayer?
		m_robots[m_nRobots] = new Robot(this, r, c);
		m_nRobots++;
		status = true;
	}
	return status;
}

bool Arena::addPlayer(int r, int c)
{
	// Don't add a player if one already exists
	if (m_player != nullptr)
		return false;

	// Dynamically allocate a new Player and add it to the arena
	m_player = new Player(this, r, c);
	return true;
}

//PROBLEM IS IN HERE
void Arena::damageRobotAt(int r, int c)
{
	// TODO:  Damage one robot at row r, column c if at least one is there.
	// If the robot does not survive the damage, destroy it.
	bool status = false;
	int counter = 0;
	for (int i = 0; i < m_nRobots; i++)
	{
		if(nRobotsAt(r,c) > 0)
			if (c == m_robots[i]->col())
				if (r == m_robots[i]->row())
				{
					status = m_robots[i]->takeDamageAndLive();
					break;
				}
		counter++;
	}

	if (status == false)
	{		
		delete m_robots[counter];
		m_robots[counter] = m_robots[--m_nRobots];
	}
	else
		return;
}

bool Arena::moveRobots()
{
	for (int k = 0; k < m_nRobots; k++)
	{
		m_robots[k]->move();
		if (m_robots[k]->row() == m_player->row())
			if (m_robots[k]->col() == m_player->col())
				m_player->setDead();
		// TODO:  Have the k-th robot in the arena make one move.
		//        If that move results in that robot being in the same
		//        position as the player, the player dies.
	}

	// return true if the player is still alive, false otherwise
	return !m_player->isDead();
}