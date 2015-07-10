package com.l5r.gm.object;

import java.util.List;
import java.util.Observable;

import com.l5r.gm.model.Constants;

public class Character extends Observable {

	private String _firstName;

	private String _lastName;

	private int _rank;

	private EClan _clan;

	private int _xp;

	private int _pendingXpPoints;

	// TODO _school;

	// TODO _insight;

	private int _honor;

	private int _glory;

	private int _status;

	private int _shadowlandTaint;

	private int _pv;

	private int _earthPoints;

	private int _stamina;

	private int _willPower;

	private int _waterPoints;

	private int _strength;

	private int _perception;

	private int _airPoints;

	private int _reflexes;

	private int _awareness;

	private int _firePoints;

	private int _agility;

	private int _intelligence;

	private int _voidPoints;

	private int[] _statistics;

	private Weapon _weapon1;

	private Weapon _weapon2;

	private Arrow _arrows;

	private List<Skill> _skills;

	private List<Trait> _advantages;

	private List<Trait> _disadvantages;

	public Character(String firstName_p, String lastName_p, EClan clan_p) {
		_firstName = firstName_p;
		_lastName = lastName_p;
		_clan = clan_p;
		_rank = 1;
		_statistics = new int[Constants.NB_OF_STATS];
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public int getRank() {
		return _rank;
	}

	public void setRankUp() {
		_rank++;
		setChanged();
		notifyObservers(Constants.RANK_UP);
	}

	public EClan getClan() {
		return _clan;
	}

	public int getXp() {
		return _xp;
	}

	public void addXp(int xp_p) {
		_xp += xp_p;
		setChanged();
		notifyObservers(Constants.XP_ADDED);
	}

	public int getPendingXpPoints() {
		return _pendingXpPoints;
	}

	public void addPendingXpPoints(int pendingXpPoints_p) {
		_pendingXpPoints += pendingXpPoints_p;
		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getHonor() {
		return _honor;
	}

	public void addHonor(int honor_p) {
		if (_honor < Constants.STAT_MAX_VALUE) {
			_honor += honor_p;
		}
	}

	public void removeHonor(int honor_p) {
		if (_honor > Constants.STAT_MIN_VALUE) {
			_honor -= honor_p;
		}
	}

	public int getGlory() {
		return _glory;
	}

	public void addGlory(int glory_p) {
		if (_glory < Constants.STAT_MAX_VALUE) {
			_glory += glory_p;
		}
	}

	public void removeGlory(int glory_p) {
		if (_glory > Constants.STAT_MIN_VALUE) {
			_glory -= glory_p;
		}
	}

	public int getStatus() {
		return _status;
	}

	public void addStatus(int status_p) {
		if (_status < Constants.STAT_MAX_VALUE) {
			_status += status_p;
		}
	}

	public void removeStatus(int status_p) {
		if (_status > Constants.STAT_MIN_VALUE) {
			_status -= status_p;
		}
	}

	public int getShadowlandTaint() {
		return _shadowlandTaint;
	}

	public void addShadowlandTaint(int shadowlandTaint_p) {
		if (_shadowlandTaint < Constants.STAT_MAX_VALUE) {
			_shadowlandTaint += shadowlandTaint_p;
		}
	}

	public void removeShadowlandTaint(int shadowlandTaint_p) {
		if (_shadowlandTaint > Constants.STAT_MIN_VALUE) {
			_shadowlandTaint -= shadowlandTaint_p;
		}
	}

	public int getPv() {
		return _pv;
	}

	public void modifyPv(int pv_p) {
		_pv += pv_p;
		setChanged();
		notifyObservers(Constants.PV_CHANGED);
	}

	public int getEarthPoints() {
		return _earthPoints;
	}

	private void computeEarthPoints() {
		_earthPoints = Math.min(_stamina, _willPower);
		_statistics[Constants.EARTH_INDEX] = _earthPoints;
		setChanged();
		notifyObservers(Constants.EARTH_INDEX);
	}

	public int getStamina() {
		return _stamina;
	}

	public void addStamina(int stamina_p) {
		_stamina += stamina_p;
		computeEarthPoints();
		computePendingXpPointsFromStamina(stamina_p);
		_statistics[Constants.STAMINA_INDEX] = _stamina;
		setChanged();
		notifyObservers(Constants.STAMINA_INDEX);
	}

	private void computePendingXpPointsFromStamina(int stamina_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getWillPower() {
		return _willPower;
	}

	public void addWillPower(int willPower_p) {
		_willPower += willPower_p;
		computeEarthPoints();
		computePendingXpPointsFromWillPower(willPower_p);
		_statistics[Constants.WILLPOWER_INDEX] = _willPower;
		setChanged();
		notifyObservers(Constants.WILLPOWER_INDEX);
	}

	private void computePendingXpPointsFromWillPower(int willPower_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getWaterPoints() {
		return _waterPoints;
	}

	private void computeWaterPoints() {
		_waterPoints = Math.min(_strength, _perception);
		_statistics[Constants.WATER_INDEX] = _waterPoints;
		setChanged();
		notifyObservers(Constants.WATER_INDEX);
	}

	public int getStrength() {
		return _strength;
	}

	public void addStrength(int strength_p) {
		_strength += strength_p;
		computeWaterPoints();
		computePendingXpPointsFromStrength(strength_p);
		_statistics[Constants.STRENGTH_INDEX] = _strength;
		setChanged();
		notifyObservers(Constants.STRENGTH_INDEX);
	}

	private void computePendingXpPointsFromStrength(int strength_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getPerception() {
		return _perception;
	}

	public void addPerception(int perception_p) {
		_perception += perception_p;
		computeWaterPoints();
		computePendingXpPointsFromPerception(perception_p);
		_statistics[Constants.PERCEPTION_INDEX] = _perception;
		setChanged();
		notifyObservers(Constants.PERCEPTION_INDEX);
	}

	private void computePendingXpPointsFromPerception(int perception_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getAirPoints() {
		return _airPoints;
	}

	private void computeAirPoints() {
		_airPoints = Math.min(_reflexes, _awareness);
		_statistics[Constants.AIR_INDEX] = _airPoints;
		setChanged();
		notifyObservers(Constants.AIR_INDEX);
	}

	public int getReflexes() {
		return _reflexes;
	}

	public void addReflexes(int reflexes_p) {
		_reflexes += reflexes_p;
		computeAirPoints();
		computePendingXpPointsFromReflexes(reflexes_p);
		_statistics[Constants.REFLEXES_INDEX] = _reflexes;
		setChanged();
		notifyObservers(Constants.REFLEXES_INDEX);
	}

	private void computePendingXpPointsFromReflexes(int reflexes_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getAwareness() {
		return _awareness;
	}

	public void addAwareness(int awareness_p) {
		_awareness += awareness_p;
		computeAirPoints();
		computePendingXpPointsFromAwareness(awareness_p);
		_statistics[Constants.AWARENESS_INDEX] = _awareness;
		setChanged();
		notifyObservers(Constants.AWARENESS_INDEX);
	}

	private void computePendingXpPointsFromAwareness(int awareness_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getFirePoints() {
		return _firePoints;
	}

	private void computeFirePoints() {
		_firePoints = Math.min(_agility, _intelligence);
		_statistics[Constants.FIRE_INDEX] = _firePoints;
		setChanged();
		notifyObservers(Constants.FIRE_INDEX);
	}

	public int getAgility() {
		return _agility;
	}

	public void addAgility(int agility_p) {
		_agility += agility_p;
		computeFirePoints();
		computePendingXpPointsFromAgility(agility_p);
		_statistics[Constants.AGILITY_INDEX] = _agility;
		setChanged();
		notifyObservers(Constants.AGILITY_INDEX);
	}

	private void computePendingXpPointsFromAgility(int agility_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getIntelligence() {
		return _intelligence;
	}

	public void addIntelligence(int intelligence_p) {
		_intelligence += intelligence_p;
		computeFirePoints();
		computePendingXpPointsFromIntelligence(intelligence_p);
		_statistics[Constants.INTELLIGENCE_INDEX] = _intelligence;
		setChanged();
		notifyObservers(Constants.INTELLIGENCE_INDEX);
	}

	private void computePendingXpPointsFromIntelligence(int intelligence_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getVoidPoints() {
		return _voidPoints;
	}

	public void addVoidPoints(int voidPoints_p) {
		if (_voidPoints < Constants.STAT_MAX_VALUE - 1) {
			_voidPoints += voidPoints_p;
			computePendingXpPointsFromVoid(voidPoints_p);
			_statistics[Constants.VOID_INDEX] = _voidPoints;
			setChanged();
			notifyObservers(Constants.VOID_INDEX);
		}
	}

	private void computePendingXpPointsFromVoid(int voidPoints_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public void removeVoidPoints(int voidPoints_p) {
		if (_voidPoints > Constants.STAT_MIN_VALUE) {
			_voidPoints -= voidPoints_p;
			_statistics[Constants.VOID_INDEX] = _voidPoints;
		}
	}

	public Weapon getWeapon1() {
		return _weapon1;
	}

	public void setWeapon1(Weapon weapon1_p) {
		_weapon1 = weapon1_p;
	}

	public Weapon getWeapon2() {
		return _weapon2;
	}

	public void setWeapon2(Weapon weapon2_p) {
		_weapon2 = weapon2_p;
	}

	public Arrow getArrows() {
		return _arrows;
	}

	public void setArrows(Arrow arrows_p) {
		_arrows = arrows_p;
	}

	public List<Skill> getSkills() {
		return _skills;
	}

	public void addSkill(Skill skill_p) {
		_skills.add(skill_p);
		computePendingXpPointsFromSkillAddition(skill_p);
	}

	private void computePendingXpPointsFromSkillAddition(Skill skill_p) {
		// TODO Auto-generated method stub

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public List<Trait> getAdvantages() {
		return _advantages;
	}

	public void addAdvantage(Trait advantage_p) {
		_advantages.add(advantage_p);
		_pendingXpPoints -= advantage_p.getPoints();
		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public List<Trait> getDisadvantages() {
		return _disadvantages;
	}

	public void addDisadvantages(Trait disadvantage_p) {
		_disadvantages.add(disadvantage_p);
		_pendingXpPoints += disadvantage_p.getPoints();
		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getStatistic(int statIndex_p) {
		return _statistics[statIndex_p];
	}

	@Override
	public boolean equals(Object o_p) {
		boolean equals = false;
		if (o_p instanceof Arrow) {
			Character obj = (Character) o_p;
			equals = obj.getFirstName().equals(_firstName) && obj.getLastName().equals(_lastName)
					&& obj.getRank() == _rank && obj.getXp() == _xp;
		}
		return equals;
	}

	@Override
	public String toString() {
		return _lastName + " " + _firstName + ", Rank: " + _rank;
	}

}
