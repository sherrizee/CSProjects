#ifndef ARENA_H
#define ARENA_H

class Player;
class Robot;

#include "Globals.h"
#include <string>

class Arena
{
public:
	// Constructor/destructor
	Arena(int nRows, int nCols);
	~Arena();

	// Accessors
	int     rows() const;
	int     cols() const;
	Player* player() const;
	int     robotCount() const;
	int     nRobotsAt(int r, int c) const;
	void    display(std::string msg) const;
	void	displayRules() const;

	// Mutators
	bool   addRobot(int r, int c);
	bool   addPlayer(int r, int c);
	void   damageRobotAt(int r, int c);
	bool   moveRobots();

private:
	int     m_rows;
	int     m_cols;
	Player* m_player;
	Robot*  m_robots[MAXROBOTS];
	int     m_nRobots;
};

#endif