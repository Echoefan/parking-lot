package com.parkinglot.entity.manager;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by victor on 05/05/2017.
 */

public class ReservationEntity implements Serializable {
  
	private static final long serialVersionUID = 1L;
	private int reservationId;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp arrivalTime;
    private Timestamp departTime;
    private int resState;
    private int userId;
    private String carNumber;

    public ReservationEntity() {
    }

    public ReservationEntity(int reservationId, Timestamp startTime, Timestamp endTime, Timestamp arrivalTime,
			Timestamp departTime, int resState, int userId, String carNumber) {
		super();
		this.reservationId = reservationId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.arrivalTime = arrivalTime;
		this.departTime = departTime;
		this.resState = resState;
		this.userId = userId;
		this.carNumber = carNumber;
	}

	public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Timestamp departTime) {
        this.departTime = departTime;
    }

    public int getResState() {
        return resState;
    }

    public void setResState(int resState) {
        this.resState = resState;
    }

    public int getuserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
    
    @Override
	public String toString() {
		return "ReservationEntity [reservationId=" + reservationId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", arrivalTime=" + arrivalTime + ", departTime=" + departTime + ", resState=" + resState + ", userId="
				+ userId + ", carNumber=" + carNumber + "]";
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationEntity other = (ReservationEntity) obj;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (carNumber == null) {
			if (other.carNumber != null)
				return false;
		} else if (!carNumber.equals(other.carNumber))
			return false;
		if (departTime == null) {
			if (other.departTime != null)
				return false;
		} else if (!departTime.equals(other.departTime))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (resState != other.resState)
			return false;
		if (reservationId != other.reservationId)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((carNumber == null) ? 0 : carNumber.hashCode());
		result = prime * result + ((departTime == null) ? 0 : departTime.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + resState;
		result = prime * result + reservationId;
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + userId;
		return result;
	}
}
