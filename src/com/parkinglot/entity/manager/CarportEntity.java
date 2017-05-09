package com.parkinglot.entity.manager;

import java.io.Serializable;

public class CarportEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int carportId;
    private int occupation;

    public CarportEntity() {
    }

	public CarportEntity(int carportId, int occupation) {
		super();
		this.carportId = carportId;
		this.occupation = occupation;
	}

	public int getCarportId() {
		return carportId;
	}

	public void setCarportId(int carportId) {
		this.carportId = carportId;
	}

	public int getOccupation() {
		return occupation;
	}

	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CarportEntity [carportId=" + carportId + ", occupation=" + occupation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carportId;
		result = prime * result + occupation;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarportEntity other = (CarportEntity) obj;
		if (carportId != other.carportId)
			return false;
		if (occupation != other.occupation)
			return false;
		return true;
	}
    
}
