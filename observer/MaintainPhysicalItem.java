package observer;

import factory.PhysicalItem;

import java.io.FileWriter;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class MaintainPhysicalItem {
    public ArrayList<PhysicalItem> physicalItems = new ArrayList<PhysicalItem>();

    public void load(String path) throws Exception {
        CsvReader reader = new CsvReader(path);
        reader.readHeaders();

        while (reader.readRecord()) {
            String itemType = reader.get("itemType");
            PhysicalItem item = null;

            switch (itemType) {
                case "HardcoverBook":
                    item = new HardcoverBook();
                    break;
                case "Magazine":
                    item = new Magazine();
                    break;
                case "CD":
                    item = new CD();
                    break;
                default:
                    break;
            }

            if (item != null) {
                item.setTitle(reader.get("title"));
                item.setLocation(reader.get("location"));
                item.setRentable(Boolean.parseBoolean(reader.get("rentable")));
                item.setPurchaseable(Boolean.parseBoolean(reader.get("purchaseable")));
                item.setPrice(Double.parseDouble(reader.get("price")));

                if (item instanceof HardcoverBook) {
                    ((HardcoverBook) item).setAttributes(
                            reader.get("title"),
                            reader.get("author"),
                            reader.get("publisher"),
                            reader.get("ISBN"),
                            reader.get("location"),
                            Boolean.parseBoolean(reader.get("rentable")),
                            Boolean.parseBoolean(reader.get("purchaseable")),
                            Double.parseDouble(reader.get("price"))
                    );
                } else if (item instanceof Magazine) {
                    ((Magazine) item).setAttributes(
                            reader.get("title"),
                            reader.get("publisher"),
                            reader.get("location"),
                            Boolean.parseBoolean(reader.get("rentable")),
                            Boolean.parseBoolean(reader.get("purchaseable")),
                            Double.parseDouble(reader.get("price"))
                    );
                } else if (item instanceof CD) {
                    ((CD) item).setAttributes(
                            reader.get("title"),
                            reader.get("location"),
                            Boolean.parseBoolean(reader.get("rentable")),
                            Boolean.parseBoolean(reader.get("purchaseable")),
                            Double.parseDouble(reader.get("price"))
                    );
                }

                physicalItems.add(item);
            }
        }
    }

    public void update(String path) throws Exception {
        try {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(path, false), ',');

            csvOutput.write("itemType");
            csvOutput.write("title");
            csvOutput.write("location");
            csvOutput.write("rentable");
            csvOutput.write("purchaseable");
            csvOutput.write("price");
            csvOutput.endRecord();
            

            for (PhysicalItem item : physicalItems) {
                String itemType = item.getClass().getSimpleName();
                csvOutput.write(itemType);
                csvOutput.write(item.getTitle());
                csvOutput.write(item.getLocation());
                csvOutput.write(String.valueOf(item.getRentable()));
                csvOutput.write(String.valueOf(item.getPurchaseable()));
                csvOutput.write(String.valueOf(item.getPrice()));
                csvOutput.endRecord();
            }
            csvOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
