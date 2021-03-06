// Copyright 2015 by Ivan Popivanov
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package net.tradelib.functors;

import static org.junit.Assert.*;
import net.tradelib.functors.ZigZag;

import org.junit.Test;

public class ZigZagTest {

   @Test
   public void test() {
      String [] inputs = {"1946.16", "1946.17", "1967.90", "1964.82", "1935.10",
                          "1968.89", "1928.21", "1906.13", "1874.74", "1877.70",
                          "1862.49", "1862.76", "1886.76", "1904.01", "1941.28",
                          "1927.11", "1950.82", "1964.58", "1961.63", "1985.05",
                          "1982.30", "1994.65", "2018.05", "2017.81", "2012.10",
                          "2023.57", "2031.21", "2031.92", "2038.26", "2039.68",
                          "2038.25", "2039.33", "2039.82", "2041.32", "2051.80",
                          "2048.72", "2052.75", "2063.50", "2069.41", "2067.03",
                          "2072.83", "2067.56", "2053.44", "2066.55", "2074.33",
                          "2071.92", "2075.37", "2060.31", "2059.82", "2026.14",
                          "2035.33", "2002.33", "1989.63", "1972.74", "2012.89",
                          "2061.23", "2070.65", "2078.54", "2082.17", "2081.88",
                          "2088.77", "2090.57", "2080.35", "2058.90"};
      
      int [] expectedTrend = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
               0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,-1,-1,1,1,1,1,1,-1,-1,-1,-1,-1,
               1,1,1,1,1,1,1,1,1,1};
      int [] expectedAge = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7,0,1,0,1,2,3,4,0,
            1,2,3,4,0,1,2,3,4,5,6,7,8,9};
      double [] expectedCorr = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0.9,0,0,0,0.88,0,0.7,0,0.74,1,
            0.86,0,0.04,0.01,0,0.46,0,0,0,1,0,0,0,0,0.99,0,0,
            0.76,0.25};
      
      ZigZag zz = new ZigZag(21, 3);
      for(int ii = 0; ii < inputs.length; ++ii) {
         zz.add(Double.parseDouble(inputs[ii]));
         double corr = zz.getCorrection();
         int age = zz.getAge();
         int trend = zz.getTrend();
         // System.out.println(String.format("%d: %d, %d, %.2f, %.4f", ii+1, trend, age, corr, zz.getTarget()));
         assertEquals(zz.getTrend(), expectedTrend[ii]);
         assertEquals(zz.getAge(), expectedAge[ii]);
         assertEquals(zz.getCorrection(), expectedCorr[ii], 0.01);
      }
   }
}
