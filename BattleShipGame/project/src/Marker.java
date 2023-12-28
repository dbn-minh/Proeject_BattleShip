import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.List;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;


public class Marker extends Rectangle {

    private final Color MISS_COLOUR = new Color(26, 26, 97, 180);

    private final int PADDING = 3;

    private List<ImageIcon> hitImages; // Danh sách các hình ảnh để hiển thị khi tàu bị đánh
    private int currentImageIndex;

    private boolean showMarker;
    private Timer explosionTimer;
    private static final int EXPLOSION_DELAY = 100;

    private boolean continuousHit;
    private Thread continuousImageUpdateThread;

    private Ship shipAtMarker;

    public Marker(int x, int y, int width, int height) {
        super(x, y, width, height);
        hitImages = new ArrayList<>();
        hitImages.add(new ImageIcon("BattleShipGame/project/Image/explosion4.png"));

        explosionTimer = new Timer(EXPLOSION_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContinuousImage();
            }
        });
        explosionTimer.setRepeats(true);

        reset();
    }

    public void reset() {
        shipAtMarker = null;
        showMarker = false;
    }

    /**
     * If not previously marked it will tell the associated ship that
     * another section has been destroyed. Then mark the marker to make
     * it visible for drawing.
     */
    public void mark() {
        if (!showMarker && isShip()) {
            shipAtMarker.destroySection();
        }
        showMarker = true;
        currentImageIndex = (currentImageIndex + 1) % hitImages.size();
        continuousImageUpdateThread = new Thread(new ContinuousImageUpdate());
        continuousImageUpdateThread.start();
        explosionTimer.start();
    }

    /**
     * Gets if the marker has already been interacted with.
     *
     * @return True if the marker is visible.
     */
    public boolean isMarked() {
        return showMarker;
    }

    /**
     * Sets the ship to the specified reference. Changes the colour used
     * if this marker is revealed, and allows notification on the ship if
     * interaction to mark this marker does happen.
     *
     * @param ship Reference to the ship at this location.
     */
    public void setAsShip(Ship ship) {
        this.shipAtMarker = ship;
    }

    /**
     * Gets if this marker has an associated Ship.
     *
     * @return True if a ship has been set.
     */
    public boolean isShip() {
        return shipAtMarker != null;
    }

    /**
     * Gets the associated ship if there is one, otherwise it will be null.
     *
     * @return Reference to the associated ship for this Marker.
     */
    public Ship getAssociatedShip() {
        return shipAtMarker;
    }

    public void stopContinuousHit() {
        continuousHit = false; // Stop the continuous hit thread
        continuousImageUpdateThread = null; // Release the reference to the thread
    }

    private class ContinuousImageUpdate implements Runnable {
        @Override
        public void run() {
            while (continuousHit) {
                try {
                    // Pause for a short duration
                    Thread.sleep(100); // Đặt khoảng thời gian giữa các hình ảnh ở đây (0.1 giây trong ví dụ)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                updateContinuousImage(); // Update the image continuously
            }
        }
    }

    // public void updateContinuousImage() {
    // currentImageIndex = (currentImageIndex + 1) % hitImages.size();
    // }
    private void updateContinuousImage() {
        if (currentImageIndex < hitImages.size() - 1) {
            currentImageIndex++;
        } else {
            // Stop the timer when all frames have been drawn
            stopContinuousHit();
        }
    }

    /**
     * Does nothing if not marked.
     * Draws a rectangle to match the correct padded size of the marker.
     * Uses the colour based on whether this object is over a ship.
     *
     * @param g Reference to the Graphics object for drawing.
     */
    public void paint(Graphics g) {
        if (!showMarker)
            return;

        if (isShip() && currentImageIndex < hitImages.size()) {
            ImageIcon currentImage = hitImages.get(currentImageIndex);
            int xOffset = PADDING + 1;
            int yOffset = PADDING + 1;

            g.drawImage(currentImage.getImage(), position.x + xOffset, position.y + yOffset,
                    width - PADDING * 2, height - PADDING * 2, null);
        } else {
            // Vẽ một hình chữ nhật với màu để thể hiện lỗi đánh
            g.setColor(MISS_COLOUR);
            g.fillRect(position.x + PADDING + 1, position.y + PADDING + 1,
                    width - PADDING * 2, height - PADDING * 2);
        }

    }

}
