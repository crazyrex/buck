/*
 * Copyright 2016-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.slb;

import com.facebook.buck.core.util.immutables.BuckStyleImmutable;
import com.facebook.buck.event.AbstractBuckEvent;
import com.facebook.buck.event.EventKey;
import java.net.URI;
import java.util.Optional;
import org.immutables.value.Value;

public class LoadBalancedServiceEvent extends AbstractBuckEvent {
  private final LoadBalancedServiceEventData data;

  protected LoadBalancedServiceEvent(LoadBalancedServiceEventData data) {
    super(EventKey.unique());
    this.data = data;
  }

  public LoadBalancedServiceEventData getData() {
    return data;
  }

  @Override
  protected String getValueString() {
    return getEventName();
  }

  @Override
  public String getEventName() {
    return this.getClass().getName();
  }

  @Value.Immutable
  @BuckStyleImmutable
  interface AbstractLoadBalancedServiceEventData {
    URI getServer();

    Optional<Exception> getException();

    Optional<Long> getRequestSizeBytes();

    Optional<Long> getLatencyMicros();

    Optional<Long> getResponseSizeBytes();
  }
}
