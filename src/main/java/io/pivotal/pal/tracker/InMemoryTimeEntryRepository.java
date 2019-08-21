package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    HashMap<Long,TimeEntry> allTimesheets = new HashMap<Long,TimeEntry>();
    private Long timeEntryId = 0L;

    @Override
    public TimeEntry find(long timeEntryId) {

        TimeEntry t= allTimesheets.get(timeEntryId);
        return t;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(allTimesheets.values());
       /* List<TimeEntry> list = new ArrayList<TimeEntry>();
        for(Map.Entry<Long, TimeEntry> entry : allTimesheets.entrySet()) {
            Long key = entry.getKey();
            TimeEntry value = entry.getValue();
            list.add(value);
            System.out.println(list);
        }
        return list;*/
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        TimeEntry tt = find(eq);
        if(tt != null) {
            TimeEntry t = allTimesheets.get(eq);
            t.setId(eq);
            t.setProjectId(any.getProjectId());
            t.setUserId(any.getUserId());
            t.setDate(any.getDate());
            t.setHours(any.getHours());
            allTimesheets.replace(eq, t);
            return t;
        }
        else
            return null;

    }

    @Override
    public void delete(long timeEntryId) {
        allTimesheets.remove(timeEntryId);
    }

    @Override
   public TimeEntry create(TimeEntry timeEntry) {
        //timeEntry.setId(timeEntryId+1);

        timeEntryId= ++timeEntryId;

        TimeEntry t = new TimeEntry(timeEntryId,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        allTimesheets.put(timeEntryId,t);
        return t;
    }
}
