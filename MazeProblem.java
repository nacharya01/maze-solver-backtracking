import org.apache.commons.lang3.SystemUtils;

import java.util.*;

public class MazeProblem {
    public static void withBackTracking(List<List<Integer>> grid,
                                        List<Integer> source,
                                        Set<List<Integer>> visitedNodes,
                                        List<Integer> destination,
                                        String path,
                                        List<String>result){
        String newPath = null;

        // Base case
        if ( destination.get(0) == source.get(0) && destination.get(1) == source.get(1)){
            result.add( path);
            return;
        }

        int x = source.get(0);
        int y = source.get(1);

        // Move up
        for( int i = (x+1); i<grid.size(); i++){
            if( grid.get(i).get(y) == 1 || visitedNodes.contains(Arrays.asList(i, y)) ){
                break;
            }
            visitedNodes.add(Arrays.asList(i, y));
            newPath = path + "-->" + "["+i +","+ y+"]";
            withBackTracking(grid,Arrays.asList(i, y),visitedNodes,destination,newPath,result) ;
        }

        // Move down
        for( int i = (x-1); i>=0; i--){
            if( grid.get(i).get(y) == 1  || visitedNodes.contains(Arrays.asList(i, y)) ){
                break;
            }
            visitedNodes.add(Arrays.asList(i, y));
            newPath = path + "-->" + "["+i +","+ y+"]";
            withBackTracking(grid,Arrays.asList(i, source.get(1)),visitedNodes,destination,newPath,result);
        }

        // Move right
        for( int j = (y+1); j<grid.get(0).size(); j++){
            if( grid.get(x).get(j) == 1 || visitedNodes.contains(Arrays.asList(x, j))){
                break;
            }
            visitedNodes.add(Arrays.asList(x, j));
            newPath = path + "-->" + "["+x +","+ j+"]";
            withBackTracking(grid,Arrays.asList(x, j),visitedNodes,destination,newPath,result);
        }

        // Move left
        for( int j = (y-1); j>=0; j--){
            if( grid.get(x).get(j) == 1 || visitedNodes.contains(Arrays.asList(x, j)) ){
                break;
            }
            visitedNodes.add(Arrays.asList(x, j));
            newPath = path + "-->" + "["+x +","+ j+"]";
            withBackTracking(grid,Arrays.asList(x, j),visitedNodes,destination,newPath,result);
        }
    }

    public static void main(String[] args){

        // Maze grid
        List<List<Integer>> mazeInput = Arrays.asList(
            Arrays.asList(0,0,1,0,0),
            Arrays.asList(0,0,0,0,0),
            Arrays.asList(0,0,0,1,0),
            Arrays.asList(1,1,0,1,1),
            Arrays.asList(0,0,0,0,0)
        );

        // Source coordinates
        List<Integer> sourceCoordinates = Arrays.asList(0,4);

        //Destination coordinates
        List<Integer> destinationCoordinates = Arrays.asList(4,2);

        List<String> result = new ArrayList<>();
        Set<List<Integer>> visited = new HashSet<>();
        visited.add(sourceCoordinates);
        withBackTracking(mazeInput,sourceCoordinates,visited,destinationCoordinates,"["+sourceCoordinates.get(0) +","+ sourceCoordinates.get(1)+"]",result);

        // Checking if we found any path from source to destination.
        if( result.size() == 0 ){
            System.out.println("There doesn't exist any path for the source to destination.");
        }

        // Showing all possible paths
        // from source to the destination.
        result.stream().forEach(item ->{
            System.out.println(item + SystemUtils.LINE_SEPARATOR);
        });
    }
}