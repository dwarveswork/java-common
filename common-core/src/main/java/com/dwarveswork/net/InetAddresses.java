/*
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2020 dwarveswork
 */

package com.dwarveswork.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.function.Function;

public final class InetAddresses {

  private static final String LOCALHOST = "127.0.0.1";

  private InetAddresses() { }

  public static String getLocalInetAddress() {
    return getLocalInetAddress(address -> true);
  }

  public static String getLocalInet4Address() {
    return getLocalInetAddress(address -> address instanceof Inet4Address);
  }

  public static String getLocalInetAddress(Function<InetAddress, Boolean> filter) {
    try {
      InetAddress candidateAddress = null;
      for (Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
        NetworkInterface networkInterface = networkInterfaces.nextElement();
        for (Enumeration<InetAddress> addresses = networkInterface.getInetAddresses(); addresses.hasMoreElements(); ) {
          InetAddress address = addresses.nextElement();
          if (!address.isLoopbackAddress() && filter.apply(address)) {
            if (!address.isSiteLocalAddress()) {
              return address.getHostAddress();
            } else if (candidateAddress == null) {
              candidateAddress = address;
            }
          }
        }
        if (candidateAddress != null) {
          return candidateAddress.getHostAddress();
        }
      }
    } catch (SocketException ex) {
      return LOCALHOST;
    }
    return LOCALHOST;
  }
}
