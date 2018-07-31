package org.example.algoritms.connectionproblem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.vavr.collection.List;
import io.vavr.jackson.datatype.VavrModule;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 */

public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
//        uf();


//        socialConnections();
        successorWithDelete();
    }

    public static void successorWithDelete() {
        SuccessorWithDelete swd = new SuccessorWithDelete(4);
        List<Integer> operations = List.of(1, 2, 0);
        operations.forEach(oper -> {
            LOG.info(String.valueOf(swd.delete(oper)));
        });
    }

    public static void socialConnections() throws IOException {
        QuickUnionSocialNetworkConnectivity uf = new QuickUnionSocialNetworkConnectivity(4);
        getSocialConnections()
            .getUnions()
            .forEach(tuple -> {
                LOG.info(tuple._1 + " " + tuple._2);
                uf.union(tuple._2);
                LOG.info("is Connected" + uf.isAllConnected());
            });
    }

    public static void uf() throws IOException {
        Operations operations = getOperations();
        UnionFind unionFind = new UnionFindCanonicalElement(10);
        operations.getUnions().forEach(unionFind::union);
        operations.getFind().forEach(oper -> LOG.info(oper + " isConnected: " + unionFind.isConnected(oper)));
    }

    public static Operations getOperations() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModule(new VavrModule());
        ClassLoader classLoader = Main.class.getClassLoader();
        return mapper.readValue(new File(classLoader.getResource("quickfind-example1.yaml").getFile()), Operations.class);
    }

    public static SocialConnections getSocialConnections() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModule(new VavrModule());
        ClassLoader classLoader = Main.class.getClassLoader();
        return mapper.readValue(new File(classLoader.getResource("social-network.yaml").getFile()), SocialConnections.class);
    }
}
