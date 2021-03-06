package com.spring.results.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.results.model.Marks;

@Service
public class StudentResultsService {

    private static final Map<Integer, Marks> marksMap = new HashMap<Integer, Marks>();
    static {
        marksMap.put(1, new Marks(100, 88, 83));
        marksMap.put(2, new Marks(55, 34, 78));
        marksMap.put(3, new Marks(89, 45, 74));
        marksMap.put(4, new Marks(64, 65, 89));
        marksMap.put(5, new Marks(29, 47, 53));
        marksMap.put(6, new Marks(81, 56, 78));
        marksMap.put(7, new Marks(72, 91, 86));
        marksMap.put(8, new Marks(87, 73, 91));
        marksMap.put(9, new Marks(95, 92, 96));
        marksMap.put(10, new Marks(86, 84, 98));
    }

    @HystrixCommand(fallbackMethod="getDefaultResults")
    public Marks getStudentresults(int id) {
    	if(marksMap.containsKey(id))
    		return marksMap.get(id);
    	else
    		throw new RuntimeException("Invalid Id");
    }

    public List<Marks> getAllStudentResults() {
        List<Marks> students =  new ArrayList<Marks>();
        students.addAll(marksMap.values());
        return students;
    }
    
    private Marks getDefaultResults(int id){
    	return new Marks(35, 35, 35);
    }
}
