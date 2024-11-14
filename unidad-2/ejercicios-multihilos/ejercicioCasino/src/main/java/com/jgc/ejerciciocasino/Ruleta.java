/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jgc.ejerciciocasino;

import java.util.Random;

/**
 *
 * @author rezzt
 */
public class Ruleta {
  private Random random = new Random();
  
  public int girarRule() {
    return random.nextInt(11); // 0-10
  }
}
