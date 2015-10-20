package com.sequenceiq.cloudbreak.cloud.template.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.sequenceiq.cloudbreak.cloud.model.CloudResource;
import com.sequenceiq.cloudbreak.cloud.model.generic.DynamicModel;

public class ResourceBuilderContext extends DynamicModel {

    private String region;
    private String name;
    private int parallelResourceRequest;
    private Queue<CloudResource> networkResources = new ConcurrentLinkedQueue<>();
    private Map<Long, List<CloudResource>> computeResources = new HashMap<>();
    private boolean build;

    public ResourceBuilderContext(String name, String region, int parallelResourceRequest, boolean build) {
        this(name, region, parallelResourceRequest);
        this.build = build;
    }

    public ResourceBuilderContext(String name, String region, int parallelResourceRequest) {
        this.region = region;
        this.name = name;
        this.parallelResourceRequest = parallelResourceRequest;
    }

    public String getRegion() {
        return region;
    }

    public boolean isBuild() {
        return build;
    }

    public List<CloudResource> getNetworkResources() {
        return new ArrayList<>(networkResources);
    }

    public String getName() {
        return name;
    }

    public int getParallelResourceRequest() {
        return parallelResourceRequest;
    }

    public void addNetworkResources(List<CloudResource> resources) {
        this.networkResources.addAll(resources);
    }

    public synchronized void addComputeResources(long index, List<CloudResource> resources) {
        List<CloudResource> list = computeResources.get(index);
        if (list == null) {
            list = new ArrayList<>();
            computeResources.put(index, list);
        }
        list.addAll(resources);
    }

    public List<CloudResource> getComputeResources(long index) {
        return computeResources.get(index);
    }

}
