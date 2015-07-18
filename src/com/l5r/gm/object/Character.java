package com.l5r.gm.object;

import java.util.List;
import java.util.Observable;

import com.l5r.gm.model.Constants;

public class Character extends Observable {

	private String _firstName = "Nanashi";

	private int _rank;

	private Clan _clan;

	private Family _family;

	private int _xp;

	private int _pendingXpPoints;

	private boolean _isMale = true;

	private School _school;

	private int _insight;

	private float _honor;

	private int _glory;

	private int _status;

	private int _shadowlandTaint;

	private int _pv;

	private int[] _statistics;

	private Weapon _weapon1;

	private Weapon _weapon2;

	private Arrow _arrows;

	private List<Skill> _skills;

	private List<Trait> _advantages;

	private List<Trait> _disadvantages;

	public Character() {
		_rank = 1;
		_statistics = new int[Constants.NB_OF_STATS];
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName_p) {
		_firstName = firstName_p;
	}

	public String getLastName() {
		if (_family != null) {
			return _family.getName();
		} else {
			return "";
		}
	}

	public int getRank() {
		return _rank;
	}

	public void setRankUp() {
		_rank++;
		setChanged();
		notifyObservers(Constants.RANK_UP);
	}

	public Clan getClan() {
		return _clan;
	}

	public void setClan(Clan clan_p) {
		_clan = clan_p;
	}

	public Family getFamily() {
		return _family;
	}

	public void setFamily(Family family_p) {
		if (family_p != null) {
			_statistics[family_p.getIndexOfStat()]++;
		}
		if (_family != null) {
			_statistics[_family.getIndexOfStat()]--;
		}
		_family = family_p;
		computeAirPoints();
		computeEarthPoints();
		computeFirePoints();
		computeWaterPoints();
	}

	public boolean isMale() {
		return _isMale;
	}

	public void setMale(boolean isMale_p) {
		_isMale = isMale_p;
	}

	public School getSchool() {
		return _school;
	}

	public void setSchool(School school_p) {
		if (school_p != null) {
			_statistics[school_p.getIndexOfStat()]++;
			addHonor(school_p.getHonor());
		}
		if (_school != null) {
			_statistics[_school.getIndexOfStat()]--;
			removeHonor(_school.getHonor());
		}
		_school = school_p;
		computeAirPoints();
		computeEarthPoints();
		computeFirePoints();
		computeWaterPoints();
	}

	public int getInsight() {
		return _insight;
	}

	public void setInsight(int insight_p) {
		_insight = insight_p;
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

	public float getHonor() {
		return _honor;
	}

	public void addHonor(float honor_p) {
		if (_honor < Constants.STAT_MAX_VALUE) {
			_honor += honor_p;
		}
	}

	public void removeHonor(float honor_p) {
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
		return _statistics[Constants.EARTH_INDEX];
	}

	private void computeEarthPoints() {
		_statistics[Constants.EARTH_INDEX] = Math.min(_statistics[Constants.STAMINA_INDEX],
				_statistics[Constants.WILLPOWER_INDEX]);
		setChanged();
		notifyObservers(Constants.EARTH_INDEX);
	}

	public int getStamina() {
		return _statistics[Constants.STAMINA_INDEX];
	}

	public void addStamina() {
		_statistics[Constants.STAMINA_INDEX]++;
		computeEarthPoints();
		computePendingXpPoints(Constants.STAMINA_INDEX);
		setChanged();
		notifyObservers(Constants.STAMINA_INDEX);
	}

	private void computePendingXpPoints(int statIndex_p) {
		// TODO character pending xp

		setChanged();
		notifyObservers(Constants.PENDING_XP_CHANGED);
	}

	public int getWillPower() {
		return _statistics[Constants.WILLPOWER_INDEX];
	}

	public void addWillPower() {
		_statistics[Constants.WILLPOWER_INDEX]++;
		computeEarthPoints();
		computePendingXpPoints(Constants.WILLPOWER_INDEX);
		setChanged();
		notifyObservers(Constants.WILLPOWER_INDEX);
	}

	public int getWaterPoints() {
		return _statistics[Constants.WATER_INDEX];
	}

	private void computeWaterPoints() {
		_statistics[Constants.WATER_INDEX] = Math.min(_statistics[Constants.STRENGTH_INDEX],
				_statistics[Constants.PERCEPTION_INDEX]);
		setChanged();
		notifyObservers(Constants.WATER_INDEX);
	}

	public int getStrength() {
		return _statistics[Constants.STRENGTH_INDEX];
	}

	public void addStrength() {
		_statistics[Constants.STRENGTH_INDEX]++;
		computeWaterPoints();
		computePendingXpPoints(Constants.STRENGTH_INDEX);
		setChanged();
		notifyObservers(Constants.STRENGTH_INDEX);
	}

	public int getPerception() {
		return _statistics[Constants.PERCEPTION_INDEX];
	}

	public void addPerception() {
		_statistics[Constants.PERCEPTION_INDEX]++;
		computeWaterPoints();
		computePendingXpPoints(Constants.PERCEPTION_INDEX);
		setChanged();
		notifyObservers(Constants.PERCEPTION_INDEX);
	}

	public int getAirPoints() {
		return _statistics[Constants.AIR_INDEX];
	}

	private void computeAirPoints() {
		_statistics[Constants.AIR_INDEX] = Math.min(_statistics[Constants.REFLEXES_INDEX],
				_statistics[Constants.AWARENESS_INDEX]);
		setChanged();
		notifyObservers(Constants.AIR_INDEX);
	}

	public int getReflexes() {
		return _statistics[Constants.REFLEXES_INDEX];
	}

	public void addReflexes() {
		_statistics[Constants.REFLEXES_INDEX]++;
		computeAirPoints();
		computePendingXpPoints(Constants.REFLEXES_INDEX);
		setChanged();
		notifyObservers(Constants.REFLEXES_INDEX);
	}

	public int getAwareness() {
		return _statistics[Constants.AWARENESS_INDEX];
	}

	public void addAwareness() {
		_statistics[Constants.AWARENESS_INDEX]++;
		computeAirPoints();
		computePendingXpPoints(Constants.AWARENESS_INDEX);
		setChanged();
		notifyObservers(Constants.AWARENESS_INDEX);
	}

	public int getFirePoints() {
		return _statistics[Constants.FIRE_INDEX];
	}

	private void computeFirePoints() {
		_statistics[Constants.FIRE_INDEX] = Math.min(_statistics[Constants.AGILITY_INDEX],
				_statistics[Constants.INTELLIGENCE_INDEX]);
		setChanged();
		notifyObservers(Constants.FIRE_INDEX);
	}

	public int getAgility() {
		return _statistics[Constants.AGILITY_INDEX];
	}

	public void addAgility() {
		_statistics[Constants.AGILITY_INDEX]++;
		computeFirePoints();
		computePendingXpPoints(Constants.AGILITY_INDEX);
		setChanged();
		notifyObservers(Constants.AGILITY_INDEX);
	}

	public int getIntelligence() {
		return _statistics[Constants.INTELLIGENCE_INDEX];
	}

	public void addIntelligence() {
		_statistics[Constants.INTELLIGENCE_INDEX]++;
		computeFirePoints();
		computePendingXpPoints(Constants.INTELLIGENCE_INDEX);
		setChanged();
		notifyObservers(Constants.INTELLIGENCE_INDEX);
	}

	public int getVoidPoints() {
		return _statistics[Constants.VOID_INDEX];
	}

	public void addVoidPoints() {
		if (_statistics[Constants.VOID_INDEX] < Constants.STAT_MAX_VALUE - 1) {
			_statistics[Constants.VOID_INDEX]++;
			computePendingXpPoints(Constants.VOID_INDEX);
			setChanged();
			notifyObservers(Constants.VOID_INDEX);
		}
	}

	public void removeVoidPoints(int voidPoints_p) {
		if (_statistics[Constants.VOID_INDEX] > Constants.STAT_MIN_VALUE) {
			_statistics[Constants.VOID_INDEX]--;
			computePendingXpPoints(Constants.VOID_INDEX);
			setChanged();
			notifyObservers(Constants.VOID_INDEX);
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
		// TODO character pending xp skill add

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
			equals = obj.getFirstName().equals(_firstName)
					&& obj.getLastName().equals(_family.getName()) && obj.getRank() == _rank
					&& obj.getXp() == _xp;
		}
		return equals;
	}

	@Override
	public String toString() {
		return _family.getName() + " " + _firstName + ", Rank: " + _rank;
	}

}
