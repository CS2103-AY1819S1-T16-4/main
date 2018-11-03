package seedu.planner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.planner.commons.util.CollectionUtil.areEqualIgnoreOrder;
import static seedu.planner.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.planner.commons.util.StringUtil.convertCollectionToString;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.planner.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.planner.commons.core.Messages;
import seedu.planner.logic.CommandHistory;
import seedu.planner.model.Model;
import seedu.planner.model.module.Module;
import seedu.planner.model.module.ModuleInfo;
import seedu.planner.model.util.ModuleUtil;

//@@author RomaRomama

/**
 * Add a module to the module planner
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add current/future modules to the module planner. "
            + "Parameters: "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + PREFIX_CODE + "MODULE CODE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_YEAR + "2 "
            + PREFIX_SEMESTER + "2 "
            + PREFIX_CODE + "CS3244 ";

    public static final String MESSAGE_SUCCESS = "Added Module(s): %1$s";

    private static final String MESSAGE_EQUIVALENT = "Following Module(s) are equivalent: %1$s";
    private static final String MESSAGE_EXISTED_MODULES = "Following module(s) already exist in the planner: %1$s";
    private static final String MESSAGE_PRECLUDED_MODULES = "Following module(s) have some of its preclusion"
            + " in the planner: %1$s";
    private static final String MESSAGE_UNFULFILLED = "Following module(s) have its prerequisites not fulfilled"
            + " in the planner: %1$s";

    private final int semesterIndex;
    private final Set<Module> modulesToAdd;

    /**
     * Add module method
     */
    public AddCommand(Set<Module> modules, int index) {
        requireAllNonNull(modules, index);
        semesterIndex = index;
        modulesToAdd = modules;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        List<Module> invalidModules = new ArrayList<>();
        List<Module> existedModules = new ArrayList<>();
        List<Module> precludedModules = new ArrayList<>();
        List<Module> unfulfilledModules = new ArrayList<>();

        //Filter modules that doesn't exist
        for (Module m : modulesToAdd) {
            if (!model.isModuleOffered(m)) {
                invalidModules.add(m);
            }
        }
        modulesToAdd.removeAll(invalidModules);

        //Filter modules that already exist in the planner
        for (Module m : modulesToAdd) {
            if (model.hasModule(m)) {
                existedModules.add(m);
            }
        }
        modulesToAdd.removeAll(existedModules);

        //Filter modules that have its preclusion in the planner
        for (Module m: modulesToAdd) {
            List<ModuleInfo> preclusions = m.getPreclusions();
            for (ModuleInfo preclusion: preclusions) {
                if (model.hasModule(new Module(preclusion.getCode()))) {
                    precludedModules.add(m);
                }
            }
        }
        modulesToAdd.removeAll(precludedModules);

        //Filters all equivalent modules
        List<List<Module>> equivalentModules = ModuleUtil.findModuleEquivalences(new ArrayList<>(modulesToAdd));
        for (List<Module> lm : equivalentModules) {
            modulesToAdd.removeAll(lm);
        }

        //Filters all unfulfilled modules
        for (Module m : modulesToAdd) {
            int i = 0;
            List<Module> upToIndex = new ArrayList<>();
            while (i < semesterIndex) {
                upToIndex.addAll(model.getTakenModuleList(i));
                i++;
            }
            if (!ModuleUtil.hasFulfilledAllPrerequisites(upToIndex, m)) {
                unfulfilledModules.add(m);
            }
            if (i == 0 && !m.getPrerequisites().isEmpty()) {
                unfulfilledModules.add(m);
            }
        }
        modulesToAdd.removeAll(unfulfilledModules);

        String result = String.format(MESSAGE_SUCCESS, convertCollectionToString(modulesToAdd));

        if (!invalidModules.isEmpty()) {
            result += "\n" + String.format(Messages.MESSAGE_INVALID_MODULES, convertCollectionToString(invalidModules));
        }

        if (!existedModules.isEmpty()) {
            result += "\n" + String.format(MESSAGE_EXISTED_MODULES, convertCollectionToString(existedModules));
        }

        if (!precludedModules.isEmpty()) {
            result += "\n" + String.format(MESSAGE_PRECLUDED_MODULES, convertCollectionToString(precludedModules));
        }

        if (!equivalentModules.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (List<Module> equivalence : equivalentModules) {
                sb.append("(");
                sb.append(convertCollectionToString(equivalence));
                sb.append(") ");
            }
            result += "\n" + String.format(MESSAGE_EQUIVALENT, sb.toString().trim());
        }

        if (!unfulfilledModules.isEmpty()) {
            result += "\n" + String.format(MESSAGE_UNFULFILLED, convertCollectionToString(unfulfilledModules));
        }
        model.addModules(modulesToAdd, semesterIndex);
        model.commitModulePlanner();
        return new CommandResult(result);
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand command = (AddCommand) other;
        return areEqualIgnoreOrder(modulesToAdd, command.modulesToAdd);
    }
}
