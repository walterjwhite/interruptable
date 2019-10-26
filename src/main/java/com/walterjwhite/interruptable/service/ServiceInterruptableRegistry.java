// package com.walterjwhite.interruptable;
//
// import com.walterjwhite.interruptable.annotation.InterruptableService;
// import org.aspectj.lang.JoinPoint;
//
// import javax.sound.midi.Patch;
// import java.lang.ref.WeakReference;
// import java.util.*;
//
//// TODO: abstract this so dependency management can be applied to anything
// public class ServiceInterruptableRegistry extends InterruptableRegistry {
//    protected WeakReference<JoinPoint>[] getOrderedServices() {
//        return getOrderedSystemPatches(interruptableReferences).toArray(new
// WeakReference[interruptableReferences.size()]);
//    }
//
//    private static List<WeakReference<JoinPoint>> getOrderedSystemPatches(
//            final Set<WeakReference<JoinPoint>> interruptableReferences) {
//        final Set<ServiceDefinition> serviceDefinitions =
// getServiceDefinitions(interruptableReferences);
//
//        setupDependencies(serviceDefinitions);
//
//        final List<ServiceDefinition> orderedServiceDefinitions =
// sortPatches(prepareFirstLevel(serviceDefinitions));
//
//        checkForCycle(serviceDefinitions);
//
//        Collections.reverse(orderedServiceDefinitions);
//        //return (orderedServiceDefinitions);
//
//        final List<WeakReference<JoinPoint>> orderedReferences = new ArrayList<>();
//        orderedServiceDefinitions.stream().forEach(orderedServiceDefinition ->
// orderedReferences.add(get(interruptableReferences, orderedServiceDefinition)));
//        return orderedReferences;
//    }
//
//    private static WeakReference<JoinPoint> get(Set<WeakReference<JoinPoint>>
// serviceDefinitionReferences, ServiceDefinition serviceDefinition){
//        return serviceDefinitionReferences.stream().filter(serviceDefinitionReference ->
// serviceDefinitionReference.get().getTarget().getClass().equals(serviceDefinition.getServiceClass())).findFirst().get();
//    }
//
//    private static Set<ServiceDefinition> getServiceDefinitions(
//            final Set<WeakReference<JoinPoint>> interruptableReferences) {
//        final Set<ServiceDefinition> serviceDefinitions = new HashSet<>();
//
//        interruptableReferences.stream().forEach(interruptableReference ->
// serviceDefinitions.add(getServiceDefinition(interruptableReference)));
//
//        return (serviceDefinitions);
//    }
//
//    private static ServiceDefinition getServiceDefinition(final WeakReference<JoinPoint>
// interruptableReference) {
//        return new ServiceDefinition(interruptableReference.get().getTarget().getClass(),
// interruptableReference.get().getTarget().getClass().getAnnotation(InterruptableService.class).dependencies());
//    }
//
//    private static void setupDependencies(final Set<ServiceDefinition> serviceDefinitions) {
//        serviceDefinitions.stream().forEach(serviceDefinition ->
// Arrays.stream(serviceDefinition.getDependentServiceClasses()).forEach(dependentServiceClass ->
//                serviceDefinition.addDependency(getServiceDefinition(serviceDefinitions,
// dependentServiceClass))));
//    }
//
//    private static ServiceDefinition getServiceDefinition(final Set<ServiceDefinition>
// serviceDefinitions, final Class serviceClass) {
//        for (final ServiceDefinition serviceDefinition : serviceDefinitions) {
//            if (serviceDefinition.getServiceClass().equals(serviceClass)) {
//                return (serviceDefinition);
//            }
//        }
//
//        throw new IllegalStateException("Unable to resolve dependency for:" + serviceClass));
//    }
//
//    private static List<ServiceDefinition> sortPatches(final Set<ServiceDefinition> firstLevel) {
//        final List<ServiceDefinition> serviceDefinitions = new ArrayList<>();
//        while (!firstLevel.isEmpty()) {
//            ServiceDefinition serviceDefinition = firstLevel.iterator().next();
//            firstLevel.remove(serviceDefinition);
//
//            serviceDefinitions.add(serviceDefinition);
//
//            for (Iterator<ServiceDefinitionEdge> serviceDefinitionEdgeIterator =
// serviceDefinition.getOutEdges().iterator();
//                 serviceDefinitionEdgeIterator.hasNext(); ) {
//                ServiceDefinitionEdge patchEdge = serviceDefinitionEdgeIterator.next();
//                ServiceDefinition serviceDefinitionEdge = patchEdge.getTo();
//                serviceDefinitionEdgeIterator.remove();
//                serviceDefinitionEdge.getInEdges().remove(patchEdge);
//
//                if (serviceDefinitionEdge.getInEdges().isEmpty()) {
//                    firstLevel.add(serviceDefinitionEdge);
//                }
//            }
//        }
//
//        return (serviceDefinitions);
//    }
//
//    private static Set<ServiceDefinition> prepareFirstLevel(final Set<ServiceDefinition>
// serviceDefinitions) {
//        final Set<ServiceDefinition> firstLevel = new HashSet<>();
//        for (ServiceDefinition serviceDefinition : serviceDefinitions) {
//            if (serviceDefinition.getInEdges().size() == 0) {
//                firstLevel.add(serviceDefinition);
//            }
//        }
//
//        return (firstLevel);
//    }
//
//    private static void checkForCycle(Set<ServiceDefinition> serviceDefinitions) {
//        for (ServiceDefinition serviceDefinition : serviceDefinitions) {
//            if (!serviceDefinition.getInEdges().isEmpty()) {
//                throw new IllegalStateException("Cycle detected, blew up."));
//            }
//        }
//    }
// }
