#include "Robot.h"
#include "Arena.h"
#include "Globals.h"
#include <iostream>

using namespace std;

Robot::Robot(Arena* ap, int r, int c)
{
	if (ap == nullptr)
	{
		cout << "***** A robot must be in some Arena!" << endl;
		exit(1);
	}
	if (r < 1 || r > ap->rows() || c < 1 || c > ap->cols())
	{
		cout << "***** Robot created with invalid coordinates (" << r << ","
			<< c << ")!" << endl;
		exit(1);
	}
	m_arena = ap;
	m_row = r;
	m_col = c;
	health = 2;
}

int Robot::row() const
{
	return m_row;
}

int Robot::col() const
{
	// TODO: TRIVIAL:  Return what column the robot is at.
	return m_col; 
}

// ERROR IS HERE, CHECK THE OPERATORS IN THE CASE AGAIN 
void Robot::move()
{
	// Attempt to move in a random direction; if we can't move, don't move
	switch (rand() % 4)
	{
	case UP:
		// TODO:  Move the robot up one row if possible.
		if (m_row > 1)
			//if (m_row < m_arena->rows())
				m_row--;
		break;
	case DOWN:
		//if (m_row > 0)
			if (m_row < m_arena->rows())
				m_row++;
		break;
	case LEFT:
		if (m_col > 1)
			//if (m_col < m_arena->cols())
				m_col--;
		break;
	case RIGHT:
		//if (m_col > 0)
			if (m_col < m_arena->cols())
				m_col++;
		// TODO:  Implement the other movements.
		break;
	}
}

bool Robot::takeDamageAndLive()
{
	bool status;
	int compare = health;
	compare--;
	if (compare != 0)
		status = true;
	else
		status = false;
	health--;
	return status;

	// TODO:  If the robot has been hit once before, return false (since a
	// second hit kills a robot).  Otherwise, return true (since the robot
	// survived the damage).
}