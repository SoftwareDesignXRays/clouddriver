/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.oort.model

import com.netflix.spinnaker.oort.documentation.Empty
import com.netflix.spinnaker.oort.documentation.Nullable

/**
 * A cluster provider is an interface for the application to retrieve implementations of {@link Cluster} objects. This interface defines the common contract for which various providers may be queried
 * for their known clusters. This interface assumes implementations may span cross account.
 *
 * @author Dan Woods
 */
interface ClusterProvider<T extends Cluster> {
  /**
   * Looks up all of the clusters available to this provider.
   * Keyed on account name.
   *
   * @return set of clusters or an empty set if none exist
   */
  @Empty
  Map<String, Set<T>> getClusters()

  /**
   * Looks up all of the clusters known to this provider to be for a specified application
   * Keyed on account name.
   *
   * @param application
   * @return set of clusters or an empty set if none exist
   *
   * @param includeDetails if true, the following collections of objects will be populated; otherwise, only the names of those objects will be populated:
   *   * loadBalancers
   *   * serverGroups
   */
  @Empty
  Map<String, Set<T>> getClusters(String application, boolean includeDetails)

  /**
   * Looks up all of the clusters known to this provider to be for a specified application and within a {@link com.netflix.spinnaker.amos.AccountCredentials} registered with
   * a {@link com.netflix.spinnaker.amos.AccountCredentialsProvider}
   *
   * @param application
   * @param account name
   * @return set of clusters with load balancers and server groups populated, or an empty set if none exist
   */
  @Empty
  Set<T> getClusters(String application, String account)

  /**
   * Looks up a cluster known to this provider to be for a specified application, within a specified {@link com.netflix.spinnaker.amos.AccountCredentials}, and with the specified name.
   *
   * @param account
   * @param name
   * @return cluster with load balancers and server groups populated, or null if none exists
   */
  @Nullable
  T getCluster(String application, String account, String name)

  /**
   * Looks up a server group known to this provider, within a specified {@link com.netflix.spinnaker.amos.AccountCredentials} and region, and with the specified name.
   * @param account name
   * @param region
   * @param name
   * @return the server group or null if none exists
   */
  @Nullable
  ServerGroup getServerGroup(String account, String region, String name)
}