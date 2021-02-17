package com.nerdysoft.task.floor_layout.service;

import com.nerdysoft.task.floor_layout.model.Point;

import java.util.List;

public interface PointService {
    List<Point> getCornerPointsByRoomName();
}
