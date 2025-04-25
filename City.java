
public class City {
    private String name;
    private String station;
    
    public City(String n){
        name = n;
        station = null;
    }

    public String name(){
        return name;
    }
    
    public String toString(){
        return name;
    }
    public boolean addStation(String s){
        if(station == null){
            station = s;
            return true;
        }
        return false;
    }

    public String getStation(){
        return station;
    }
}


22 760 70
53 800 13
15 675 0
13 698 90
61 682 30
118 724 40
154 762 70
122 797 -20
109 669 -49
140 632 -49
176 604 -34
122 681 -51
154 646 -45
192 618 -34
149 721 1
197 722 1
257 611 21
291 579 -72
323 565 1
372 569 27
264 684 -52
296 651 -45
333 625 -31
378 607 -21
237 631 85
240 679 85
152 404 22
196 428 53
219 475 78
225 524 90
243 565 -55
271 522 -65
291 474 -75
303 426 -83
224 556 -55
252 514 -67
270 468 -74
284 422 -82
324 444 89
328 489 55
363 526 31
404 554 55
156 389 5
203 392 5
252 398 5
268 360 47
343 434 56
373 472 24
420 490 -3
241 297 -84
247 250 -84
258 298 -84
263 254 -84
149 351 -31
197 344 -1
178 57 64
197 102 64
215 145 64
235 188 64
196 51 64
215 96 64
233 139 64
251 182 64
459 502 -76
446 547 -76
484 489 29
530 507 29
453 579 -34
490 550 -34

    public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		System.out.println(Math.min(x, lastX) + " " + Math.min(y, lastY) + " " + Math.round(Math.toDegrees(Math.atan2(y - lastY, x - lastX))));

		lastX = x;
		lastY = y;
	}

private void paintTrains(Graphics g, int x, int y, int angle) {
		int width = 44;
		int height = 22;
		g.drawImage(Helper.rotate(trains.get("blue"), angle), x,
		 y, (int) (Math.abs(Math.cos(Math.toRadians(angle)) * width) + Math.abs(Math.sin(Math.toRadians(angle)) * height)), (int) (Math.abs(Math.sin(Math.toRadians(angle)) * width) + Math.abs(Math.cos(Math.toRadians(angle)) * height)), null);
	}
