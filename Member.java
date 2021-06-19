/*
 * Student name: XXX
 * Student ID: YYY
 * LMS username: ZZZ
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Member {

    private String name;
    private int id;
    private String email;

    public void getMember(int id, String name, String email) {

        this.id = id;
        this.name = name;
        this.email = email;
    }



}
