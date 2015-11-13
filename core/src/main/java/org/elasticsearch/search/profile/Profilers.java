/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.search.profile;

import org.elasticsearch.search.internal.ContextIndexSearcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Wrapper around several {@link Profiler}s that makes management easier. */
public final class Profilers {

    private final ContextIndexSearcher searcher;
    private final List<Profiler> profilers;

    /** Sole constructor. This {@link Profilers} instance will initiall wrap one {@link Profiler}. */
    public Profilers(ContextIndexSearcher searcher) {
        this.searcher = searcher;
        this.profilers = new ArrayList<>();
    }

    /** Switch to a new profile. */
    public void addProfiler(InternalProfileCollector collector) {
        Profiler profiler = new Profiler(collector);
        searcher.setProfiler(profiler);
        profilers.add(profiler);
    }

    /** Return the list of all created {@link Profiler}s so far. */
    public List<Profiler> getProfilers() {
        return Collections.unmodifiableList(profilers);
    }

}
