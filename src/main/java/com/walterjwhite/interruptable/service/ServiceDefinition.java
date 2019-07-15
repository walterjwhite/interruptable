// package com.walterjwhite.interruptable;
//
// import lombok.Data;
// import lombok.ToString;
// import lombok.EqualsAndHashCode;
// import lombok.RequiredArgsConstructor;
// import lombok.ToString;
//
// import java.util.HashSet;
// import java.util.Set;
//
// @RequiredArgsConstructor
// @Data
// @ToString(doNotUseGetters=true)
// public class ServiceDefinition {
//    protected final Class serviceClass;
//
//    protected final Class[] dependentServiceClasses;
//
//    @ToString.Exclude @EqualsAndHashCode.Exclude protected Set<ServiceDefinitionEdge> inEdges =
// new HashSet<>();
//
//    @ToString.Exclude @EqualsAndHashCode.Exclude protected Set<ServiceDefinitionEdge> outEdges =
// new HashSet<>();
//
//    //  @Value.Derived
//    public void addDependency(final ServiceDefinition serviceDefinition) {
//        final ServiceDefinitionEdge patchEdge = new ServiceDefinitionEdge(this,
// serviceDefinition);
//        outEdges.add(patchEdge);
//        serviceDefinition.getInEdges().add(patchEdge);
//    }
// }
