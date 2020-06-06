#include "Player.h"
#include "Arena.h"
#include "Globals.h"
#include <iostream>
#include <ctime>

using namespace std;

Player::Player(Arena* ap, int r, int c)
{
	if (ap == nullptr)
	{
		cout << "***** The player must be in some Arena!" << endl;
		exit(1);
	}
	if (r < 1 || r > ap->rows() || c < 1 || c > ap->cols())
	{
		cout << "**** Player created with invalid coordinates (" << r
			<< "," << c << ")!" << endl;
		exit(1);
	}
	m_arena = ap;
	m_row = r;
	m_col = c;
	m_age = 0;
	m_dead = false;
}

int Player::row() const
{
	// TODO: TRIVIAL:  Return what row the player is at.
	// Delete the following line and replace it with the correct code.
	return m_row;  // This implementation compiles, but is incorrect.
}

int Player::col() const
{
	// TODO: TRIVIAL:  Return what column the player is at.
	// Delete the following line and replace it with the correct code.
	return m_col;  // This implementation compiles, but is incorrect.
}

int Player::age() const
{
	// TODO:  TRIVIAL:  Return the player's age.
	// Delete the following line and replace it with the correct code.
	return m_age;  // This implementation compiles, but is incorrect.
}

// 2ND CHECK
std::string Player::takeComputerChosenTurn()
{
	// TODO:  Replace this implementation:

	// Your replacement implementation should do something intelligent
	// and return a string that describes what happened.  When you've
	// decided what action to take, take it by calling move, shoot, or stand.
	// This function must return one of the following four strings:
	//     "Moved."
	//     "Shot and hit!"
	//     "Shot and missed!"
	//     "Stood."

	// Here's one possible strategy:
	//   If moving in some direction would put me in less immediate danger
	//     than standing, then move in that direction.
	//   else shoot in the direction of the nearest robot I can hit.

	// A more aggressive strategy is possible, where you hunt down robots.

	bool status;
	int max = 9, min = 1;
	int random;
	string direction;
	srand(time(NULL));
	random = (rand() % (max - min + 1)) + min;

	if (random % 2 == 0)
	{
		switch (random)
		{
		case 2:
			status = shoot(RIGHT);
			direction = "right";
			break;
		case 4:
			status = shoot(LEFT);
			direction = "left";
			break;
		case 6: 
			status = shoot(UP);
			direction = "up";
			break;
		case 8:
			status = shoot(DOWN);
			direction = "down";
			break;
		}
		if (status == true)
			return "Shot and hit!";
			//return "Computer shot " + direction + " and hit!";
		else
			return "Shot and missed!";
			//return "Computer shot " + direction + " and missed!";
	}
	else if (random % 2 != 0)
	{
		switch (random)
		{
		case 1:
			move(RIGHT);
			direction = "right";
		case 3:
			move(LEFT);
			direction = "left";
		case 5:
			move(UP);
			direction = "up";
		case 7:
			move(DOWN);
			direction = "down";
		case 9: 
			return "Stood.";
		}
		//return "Computer moved " + direction;
		return "Moved.";
	}
}
	

void Player::stand()
{
	m_age++;
}

// ERROR IS HERE, CHECK THE OPERATORS IN THE CASE AGAIN 
void Player::move(int dir)
{
	m_age++;
	switch (dir)
	{
	case UP:
		// TODO:  Move the player up one row if possible.
		if (m_row > 1)
			//if (m_row <= m_arena->rows())
			{
				m_row--;
				break;
			}
		break;
	case DOWN:
		//if (m_row >= 1)
			if (m_row < m_arena->rows())
			{
				m_row++;
				break;
			}
		break;
	case LEFT:
		if (m_col > 1)
			//if (m_col <= m_arena->cols())
			{
				m_col--;
				break;
			}
		break;
	case RIGHT:
		//if (m_col >= 1)
			if (m_col < m_arena->cols())
			{
				m_col++;
				break;
			}
		// TODO:  Implement the other movements.
		break;
	}
}

bool Player::shoot(int dir)
{
	m_age++;
	int counter = 0;
	bool status = false;

	if (rand() % 3 == 0)  // miss with 1/3 probability
		return status;
	else
		switch (dir)
		{
		case LEFT:
			for (int i = 0; i < MAXSHOTLEN + 1; i++)
			{
				if (0 < m_arena->nRobotsAt(m_row, m_col - i))
					if (0 < (m_col - i))
					{
						status = true;
						m_arena->damageRobotAt(m_row, m_col - i);
					}
			}
			break;
		case RIGHT:
			for (int i = 0; i < MAXSHOTLEN + 1; i++)
			{
				if (0 < m_arena->nRobotsAt(m_row, m_col + i))
					if (0 < (m_col + i))
					{
						status = true;
						m_arena->damageRobotAt(m_row, m_col + i);
					}
			}
			break;
		case UP: 
			for (int i = 0; i < MAXSHOTLEN + 1; i++)
			{
				if (0 < m_arena->nRobotsAt(m_row - i, m_col))
					if (0 < m_row - i)
					{
						status = true;
						m_arena->damageRobotAt(m_row - i, m_col);
					}
			}
			break;
		case DOWN:
			for (int i = 0; i < MAXSHOTLEN + 1; i++)
			{
				if (0 < m_arena->nRobotsAt(m_row + i, m_col))
					if (0 < m_row + i)
					{
						status = true;
						m_arena->damageRobotAt(m_row + i, m_col);
					}
			}
			break;
		}

	// TODO:  Damage the nearest robot in direction dir, returning
	// true if a robot is hit and damaged, false if not hit.
	return status;  // This implementation compiles, but is incorrect.
}

bool Player::isDead() const
{
	// TODO:  TRIVIAL:  Return whether the player is dead.
	return m_dead;
}

void Player::setDead()
{
	m_dead = true;
}