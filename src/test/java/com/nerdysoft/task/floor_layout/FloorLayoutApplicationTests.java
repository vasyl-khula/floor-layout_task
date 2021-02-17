package com.nerdysoft.task.floor_layout;

import com.nerdysoft.task.floor_layout.model.Point;
import com.nerdysoft.task.floor_layout.model.Room;
import com.nerdysoft.task.floor_layout.service.impl.RoomDataValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FloorLayoutApplicationTests {

	RoomDataValidator validator=new RoomDataValidator();


	@Test
	void contextLoads() {
	}

	@Test
	public void hasEnoughCornersTest(){
		List<Point> roomPoints=new ArrayList<>();
		roomPoints.add(new Point(0,0));
		roomPoints.add(new Point(1,0));
		roomPoints.add(new Point(1,1));
//        roomPoints.add(new Point(0,0));
		Room room=new Room();
		room.setRoom(roomPoints);
		room.setName("testRoom");

		Assertions.assertFalse(validator.hasEnoughCorners(room));
	}

	@Test
	void isPointValidTest(){
		Point pointNull=null;
		Point pointX_Null=new Point(null,2);
		Point pointY_Null=new Point(2,null);
//        Point longTypeArgs=new Point(0xfffffffffffffff,Long.MAX_VALUE);

		Assertions.assertFalse(validator.isPointValid(pointNull));
		Assertions.assertFalse(validator.isPointValid(pointX_Null));
		Assertions.assertFalse(validator.isPointValid(pointY_Null));
	}

	@Test
	void areAllCornersRightTrueCases(){
		List<Point> roomPoints2=new ArrayList<>();
		roomPoints2.add(new Point(0,0));
		roomPoints2.add(new Point(1,0));
		roomPoints2.add(new Point(1,1));
		roomPoints2.add(new Point(0,1));
		Room roomTrue=new Room();
		roomTrue.setRoom(roomPoints2);
		roomTrue.setName("testRoom");

		roomTrue.getRoom().stream().forEach(System.out::println);
		Assertions.assertTrue(validator.areAllCornersRightAndGoClockWise(roomTrue));

	}

	@Test
	void areAllCornersRightFalseCases(){
		List<Point> roomPoints=new ArrayList<>();
		roomPoints.add(new Point(0,0));
		roomPoints.add(new Point(1,0));
		roomPoints.add(new Point(1,1));
		roomPoints.add(new Point(0,0));
		Room room=new Room();
		room.setRoom(roomPoints);
		room.setName("testRoomFalse");

		Assertions.assertFalse(validator.areAllCornersRightAndGoClockWise(room));
	}

	@Test
	void isDiagonal(){
		Point p1=new Point(0,0);
		Point p2=new Point(1,1);

		Point p3=new Point(1,1);
		Point p4=new Point(1,1);

		Point p5False=new Point(0,1);
		Point p6False=new Point(1,1);

		Assertions.assertTrue(validator.isDiagonal(p1,p2));
		Assertions.assertTrue(validator.isDiagonal(p3,p4));
		Assertions.assertFalse(validator.isDiagonal(p5False,p6False));
	}

	@Test
	void wallsDoNotIntersectTestTrueCase(){
		List<Point> roomPoints2=new ArrayList<>();
		roomPoints2.add(new Point(0,0));
		roomPoints2.add(new Point(3,0));
		roomPoints2.add(new Point(3,1));
		roomPoints2.add(new Point(0,1));
		Room roomTrue=new Room();
		roomTrue.setRoom(roomPoints2);
		roomTrue.setName("testRoomTrue");

		roomTrue.getRoom().stream().forEach(System.out::println);
		Assertions.assertTrue(validator.wallsDoNotIntersect(roomTrue));
	}

	@Test
	void wallsDoNotIntersectTestFalseCase(){
		List<Point> roomPoints2=new ArrayList<>();
		roomPoints2.add(new Point(0,2));
		roomPoints2.add(new Point(3,2));
		roomPoints2.add(new Point(3,3));
		roomPoints2.add(new Point(2,3));
		roomPoints2.add(new Point(2,1));
		roomPoints2.add(new Point(0,-2));
		Room room =new Room();
		room.setRoom(roomPoints2);
		room.setName("testRoom");

		room.getRoom().stream().forEach(System.out::println);
		Assertions.assertFalse(validator.wallsDoNotIntersect(room));
	}

	@Test
	void validateTest(){
		List<Point> roomPoints2=new ArrayList<>();
		roomPoints2.add(new Point(0,0));
		roomPoints2.add(new Point(3,0));
		roomPoints2.add(new Point(3,1));
		roomPoints2.add(new Point(0,1));
		Room roomTrue=new Room();
		roomTrue.setRoom(roomPoints2);
		roomTrue.setName("testRoomTrue");

		roomTrue.getRoom().stream().forEach(System.out::println);
		Assertions.assertTrue(validator.validate(roomTrue));
	}

	@Test
	void validateGoClockwise(){
		Point p1=new Point(0,0);
		Point p2=new Point(0,2);
		Point p3=new Point(2,2);

		Assertions.assertTrue(validator.goClockwise(p1,p2,p3));
		Assertions.assertFalse(validator.goClockwise(p3,p2,p1));
	}

}
