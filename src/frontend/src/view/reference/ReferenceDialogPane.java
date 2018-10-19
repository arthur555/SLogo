package view.reference;

import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import view.utils.ImageUtils;
import view.utils.PrettyUI;

import java.util.*;

class ReferenceDialogPane extends DialogPane {
    private static final double DIALOG_WIDTH = 700;
    private static final double DIALOG_HEIGHT = 500;
    private static final double TREE_VIEW_WIDTH = DIALOG_WIDTH*0.3;
    private static final double ITEM_MARGIN = 10;
    private static final double ITEM_PADDING = 5;
    private static final double REFERENCE_IMAGE_SIZE = DIALOG_WIDTH/3;

    private static final Image REFERENCE_IMAGE =
            ImageUtils.getImageFromUrl("reference_splash.png", REFERENCE_IMAGE_SIZE, REFERENCE_IMAGE_SIZE);

    ReferenceDialogPane() {
        setMinWidth(DIALOG_WIDTH);
        setMinHeight(DIALOG_HEIGHT);
        PrettyUI.lightPurplify(this);

        var root = new GridPane();
        root.setMinWidth(DIALOG_WIDTH);
        root.setMinHeight(DIALOG_HEIGHT);

        var view = new Pane();
        view.getChildren().add(generateSplash());
        var controller = new ReferenceTree(view);

        root.add(controller, 0, 0);
        root.add(view, 1, 0);
        getChildren().add(root);

        // invisible, but required to make the dialog closeable
        getButtonTypes().add(ButtonType.APPLY);
        lookupButton(ButtonType.APPLY).setVisible(false);
    }

    private Pane generateSplash() {
        var pane = new Pane();
        var imgView = new ImageView(REFERENCE_IMAGE);
        imgView.setX((DIALOG_WIDTH - TREE_VIEW_WIDTH - REFERENCE_IMAGE_SIZE)/2);
        imgView.setY((DIALOG_HEIGHT - REFERENCE_IMAGE_SIZE)/2);
        pane.getChildren().add(imgView);
        return pane;
    }

    private class ReferenceTree extends TreeView<String> {
        private static final String REF_PATH = "ref/";
        private static final String BOOLEAN_OPS = "booleanops";
        private static final String COMMANDS = "commands";
        private static final String KEYWORDS = "keywords";
        private static final String MATH_OPS = "mathops";
        private static final String QUERIES = "queries";

        private static final String NAME = "name";
        private static final String SUFFIX = "suffix";
        private static final String SEP = ",";

        private List<String> refs = Collections.unmodifiableList(
                List.of(
                        COMMANDS,
                        QUERIES,
                        MATH_OPS,
                        BOOLEAN_OPS,
                        KEYWORDS
                )
        );

        ReferenceTree(Pane pane) {
            super();
            setExpanded(true);
            setMinWidth(TREE_VIEW_WIDTH);
            setMinHeight(DIALOG_HEIGHT);

            var pack = new LinkedHashMap<String, Map<String, VBox>>();
            refs.forEach(r -> { var sg = subgroup(r); pack.put(sg.getKey(), sg.getValue()); });

            var rootItem = new TreeItem<>("SLogo");
            for(var name : pack.keySet()) {
                var subtreeItem = new TreeItem<>(name);
                for(var id : pack.get(name).keySet()) {
                    subtreeItem.getChildren().add(new TreeItem<>(id));
                } rootItem.getChildren().add(subtreeItem);
            } setRoot(rootItem);

            getSelectionModel().selectedItemProperty().addListener((e, o, n) -> {
                if(n.getChildren().size() == 0) {
                    pane.getChildren().clear();
                    pane.getChildren().add(pack.get(n.getParent().getValue()).get(n.getValue()));
                }
            });
        }

        private Pair<String, Map<String, VBox>> subgroup(String name) {
            var bundle = ResourceBundle.getBundle(REF_PATH + name);

            var repName = bundle.getString(NAME);
            var suffixes = bundle.getString(SUFFIX).split(SEP);

            return new Pair<>(repName, extractGroupInfo(bundle, suffixes));
        }

        private Map<String, VBox> extractGroupInfo(ResourceBundle bundle, String[] suffixes) {
            var keyIt = bundle.getKeys().asIterator();
            var keys = new TreeSet<String>();
            while(keyIt.hasNext()) { // find unique set of name of commands
                var s = keyIt.next();
                for(var suffix : suffixes) s = s.replaceAll(suffix, "");
                if(!s.equals(SUFFIX) && !s.equals(NAME)) keys.add(s);
            }

            var groupInfo = new HashMap<String, VBox>();
            for(var key : keys) { // organize pack
                var info = new LinkedHashMap<String, String>();
                for(var suffix : suffixes) info.put(suffix, bundle.getString(key+suffix));
                groupInfo.put(key, createPage(info));
            } return groupInfo;
        }

        private VBox createPage(HashMap<String, String> info) {
            var box = new VBox(ITEM_MARGIN);
            for(var prop : info.keySet()) {
                var item = new VBox(ITEM_PADDING);
                item.getChildren().add(PrettyUI.h1(prop));
                item.getChildren().add(PrettyUI.p(info.get(prop), DIALOG_WIDTH - TREE_VIEW_WIDTH));
                PrettyUI.purplify(item);
                box.getChildren().add(item);
            } return box;
        }
    }
}

