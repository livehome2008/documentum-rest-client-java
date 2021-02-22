/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.xml.jaxb;

import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Repository;

@XmlRootElement(name = "repository")
public class JaxbRepository extends JaxbDmLinkableBase implements Repository {
    private int id;
    private String name;
    private String description;
    private List<Server> servers;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    @XmlElement(name="server", type=JaxbRepository.JaxbServer.class)
    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    @XmlRootElement(name = "server")
    public static class JaxbServer implements Repository.Server {
        private String name;
        private String host;
        private String version;
        private String docbroker;
        @Override
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String getHost() {
            return host;
        }
        public void setHost(String host) {
            this.host = host;
        }
        @Override
        public String getVersion() {
            return version;
        }
        public void setVersion(String version) {
            this.version = version;
        }
        @Override
        public String getDocbroker() {
            return docbroker;
        }
        public void setDocbroker(String docbroker) {
            this.docbroker = docbroker;
        }

        @Override
        public boolean equals(Object obj) {
            JaxbServer that = (JaxbServer) obj;
            return Equals.equal(name, that.name)
                    && Equals.equal(host, that.host)
                    && Equals.equal(version, that.version)
                    && Equals.equal(docbroker, that.docbroker);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, host, version, docbroker);
        }
    }

    @Override
    public boolean equals(Object obj) {
        JaxbRepository that = (JaxbRepository) obj;
        return Equals.equal(id, that.id)
                && Equals.equal(name, that.name)
                && Equals.equal(description, that.description)
                && Equals.equal(servers, that.servers)
                && Equals.equal(links, that.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, servers);
    }
}