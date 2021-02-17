package com.nerdysoft.task.floor_layout.service.impl;

import com.nerdysoft.task.floor_layout.exception.RoomValidationException;
import com.nerdysoft.task.floor_layout.model.Direction;
import com.nerdysoft.task.floor_layout.model.Point;
import com.nerdysoft.task.floor_layout.model.Room;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class RoomDataValidator {

    public boolean validate(Room room) throws RoomValidationException{
        if(room==null)
            throw new RoomValidationException("room is null");
        if( ! hasEnoughCorners(room))
            throw new RoomValidationException("do not have enough corners");
        if(room.getRoom().stream().anyMatch(point -> !isPointValid(point))){
            throw new RoomValidationException("contains invalid points");
        }
        if ( ! areAllCornersRightAndGoClockWise(room)){
            return false;
        }
        if( ! wallsDoNotIntersect(room)){
            throw new RoomValidationException("detected walls intersecting !!!");
        }
        return true;
    }

    public boolean hasEnoughCorners(Room room){
        return room.getRoom().size() >= 4;
    }

    public boolean isPointValid(Point point){

        if (point==null || point.getX()==null || point.getY()==null)
            return false;
        return true;
    }

    /**
     * this method check step by step all lines
     * @param room
     * @return true if all corners are right
     */
    public boolean areAllCornersRightAndGoClockWise(Room room){
        System.out.println("=======areAllCornersRightAndGoClockWise=======");
        List<Point> points=room.getRoom();
        int firstPoint_X;
        int firstPoint_Y;
        int secondPoint_X;
        int secondPoint_Y;
        int thirdPoint_Y;
        int thirdPoint_X;
        int goClockwiseCounter=0;
        int doNotGoClockwiseCounter=0;
        Point start=points.get(0);

        for(int i=0;i<=points.size()-2;i++){
            Point first=points.get(i);
            Point second=points.get(i+1);
            if(first.equals(second)) {
                return false;
            }
            Point third;

            firstPoint_X= first.getX();
            firstPoint_Y=first.getY();
            secondPoint_X=second.getX();
            secondPoint_Y=second.getY();
            if(i==points.size()-2){
                third=start;
                thirdPoint_X=start.getX();
                thirdPoint_Y=start.getY();
            }else{
                third=points.get(i+2);
                thirdPoint_X=third.getX();
                thirdPoint_Y=third.getY();
            }

            if( isDiagonal(first,second) | isDiagonal(second,third) )
                throw new RoomValidationException("diagonals walls are not allowed");

            if(goClockwise(first,second,third)){
                goClockwiseCounter++;
            }else
                doNotGoClockwiseCounter++;

            if((firstPoint_X==secondPoint_X && secondPoint_Y==thirdPoint_Y)){
                continue;
            }
            if ((firstPoint_Y==secondPoint_Y && secondPoint_X==thirdPoint_X)){
                continue;
            }
            System.out.println("False:"+first.toString()+second.toString()+third.toString());
            return false;
        }

        if(doNotGoClockwiseCounter>=goClockwiseCounter)
            throw new RoomValidationException("an infinite room");

        return true;
    }

    /**
     * if both points do not have equals coordinates (X or Y) the line goes diagonally
     * @param p1
     * @param p2
     * @return true if line goes diagonally
     */
    public boolean isDiagonal(Point p1, Point p2){

        if (p1.equals(p2))
            throw new RoomValidationException("two identical points");

        if(p1.getX().equals(p2.getX()) || p1.getY().equals(p2.getY()))
            return false;
        else
            return true;
    }

    public boolean goClockwise(Point p1,Point p2,Point p3){
        Direction directionFirst=resolveDirectionLine(p1,p2);
        Direction directionSecond=resolveDirectionLine(p2,p3);

        if(directionFirst.equals(Direction.UP)){
           if(directionSecond.equals(Direction.RIGHT))
               return true; // goes clockwise
           else
               return false;
        }
        if(directionFirst.equals(Direction.DOWN)){
            if(directionSecond.equals(Direction.LEFT))
                return true;
            else
                return false;
        }
        if(directionFirst.equals(Direction.RIGHT)){
            if(directionSecond.equals(Direction.DOWN))
                return true;
            else
                return false;
        }
        if(directionFirst.equals(Direction.LEFT)){
            if(directionSecond.equals(Direction.UP))
                return true;
            else
                return false;
        }
        throw new RoomValidationException("can not validate directions");
    }

    public Direction resolveDirectionLine(Point p1, Point p2){
        if(p1.getX().equals(p2.getX())){
            if(p1.getY()< p2.getY()){
                return Direction.UP;
            }else{
                return Direction.DOWN;
            }
        }else{
            if(p1.getX()> p2.getX()){
                return Direction.LEFT;
            }else
                return Direction.RIGHT;
        }
    }

    public boolean wallsDoNotIntersect(Room room){
        Set<Point> linesPoints=new HashSet<>();
        List<Point> corners=room.getRoom();
        System.out.println("=========wallsDoNotIntersect=====");

        for(int i=0; i<corners.size()-1; i++){
            Point p1=corners.get(i);
            Point p2=corners.get(i+1);
            if(p1.getX().equals(p2.getX())){
                int X_coordinate=p1.getX();
                List<Integer> Y_coordinates;
                if(p1.getY()>p2.getY()){
                    Y_coordinates= IntStream.range(p2.getY(),p1.getY())
                            .boxed()
                            .collect(Collectors.toList());
                }else {
                    Y_coordinates= IntStream.range(p1.getY(),p2.getY())
                            .boxed()
                            .collect(Collectors.toList());
                }

                for(int j=0; j<Y_coordinates.size(); j++){
                    Point linePoint=new Point(X_coordinate,Y_coordinates.get(j));

                    if(linesPoints.add(linePoint)){
                        continue;
                    }else
                        return false;
                }
                continue;
            }
            if(p1.getY().equals(p2.getY())){
                int Y_coordinate=p1.getY();
                List<Integer> X_coordinates;
                if(p1.getX()>p2.getX()){
                    X_coordinates= IntStream.range(p2.getX(),p1.getX())
                            .boxed()
                            .collect(Collectors.toList());
                }else {
                    X_coordinates= IntStream.range(p1.getX(),p2.getX())
                            .boxed()
                            .collect(Collectors.toList());
                }
                for(int j=0; j<X_coordinates.size(); j++){
                    Point linePoint=new Point(X_coordinates.get(j),Y_coordinate);
                    if(linesPoints.add(linePoint)){
                        continue;
                    }else
                        return false;
                }
                continue;
            }
        }
        return true;
    }

}










