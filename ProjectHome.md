# SUMI (Simple Users Management Interface) #

SUMI is a simple tool for java applications that requires user administration. SUMI uses a flexible and modified JXTreeTable for showing user information.

It also provides a mechanism for attaching _actions_ to each node of the tree, making it easy to interact with user objects.

It extends widely JXTreeTable and its model, and includes custom renderers and editors as a default option for interacting with colors, booleans, text, comboboxes, etc.

The extended tree widget could be reused and be a project itself for applications that need to show data with multiple kinds of different cell-data disregarding the table structure based on columns. Renderers and editors are reused at runtime avoiding the extensive use of memory.

For a brief inside take a quick look at the TellapicNodeAction wiki page.

Here is an example showing the feature to import/export data from/to an XML file:

```xml

```
<?xml version="1.0" encoding="UTF-8"?>
<tree>
    <TellapicNode id="1" name="TellapicNode1" icon_src="/icons/user.png">
        <TellapicNodeAction name="action1" type="boolean"></TellapicNodeAction>
    </TellapicNode>

    <TellapicNode id="2" name="TellapicNode2" icon_src="/icons/user.png">
        <TellapicNodeAction name="combo1" type="combo"><ComboData>1</ComboData>
            <ComboData>One</ComboData>
            <ComboData>Two</ComboData>
            <ComboData>Three</ComboData>
            <ComboData>Foo</ComboData>
         </TellapicNodeAction>
    </TellapicNode>

    <TellapicNode id="3" name="TellapicNode3" icon_src="/icons/user.png">
        <TellapicNodeAction name="Some editable information" type="text"></TellapicNodeAction>
    </TellapicNode>

    <TellapicNode id="4" name="TellapicNode4" icon_src="/icons/user.png">
       <TellapicNodeAction name="foo bar" type="color" editable="true">
           <ColorData>#ffaabb</ColorData>
       </TellapicNodeAction>
       <TellapicNodeAction name="foo bar not editable" type="text" editable="false"></TellapicNodeAction>
       <TellapicNodeAction name="click me!" type="boolean"></TellapicNodeAction>
    </TellapicNode>

    <TellapicNode id="5" name="TellapicNode5" icon_src="/icons/user.png">
        <TellapicNodeAction name="" type="combo">
            <ComboData>Here</ComboData>
            <ComboData>Spock</ComboData>
            <ComboData>Foo</ComboData>
            <ComboData>Bar</ComboData>
            <ComboData>man</ComboData>
        </TellapicNodeAction>
        <TellapicNode id="6" name="TellapicNode6" icon_src="/icons/user.png">
            <TellapicNode id="7" name="TellapicNode7" icon_src="/icons/user.png">
                <TellapicNodeAction name="combo7" type="combo">
                    <ComboData>There</ComboData>
                    <ComboData>You</ComboData>
                    <ComboData>Are</ComboData>
                </TellapicNodeAction>
            </TellapicNode>
        </TellapicNode>
    </TellapicNode>
</tree>
```
```

and produces this tree view:

![http://i.imgur.com/LlCcO.png](http://i.imgur.com/LlCcO.png)

You can also have something weird like this:

![http://i.imgur.com/H8XqN.png](http://i.imgur.com/H8XqN.png)

Or use it in order to provide user's process information:

![http://i.imgur.com/hIqDG.png](http://i.imgur.com/hIqDG.png)

---


# Editors and Renderers #

For performance issues, SUMI uses just 1 editor/renderer of a kind. You can register or add your own editor or renderer to the treetable. You need to follow some basic steps that should be documented soon to understand the concept behind this extension. **It's very important** to understand the genesis and facts of the project, as it's not strictly based on the table concept of columns and rows. Registering such objects is easily achieved by invoking, for instance, this piece of code:

`tree.registerEditorComponent(MY_EDITOR_KEY, new MyCellEditor());`

SUMI lets you forget about that contraint that tables puts on column, e.g. _manage the same kind of data_.

You need to _asume_ that SUMI provides information, actions, and behaviour for just an object with the ability to have hierarchical display. Generally speaking, SUMI provides _actions_ regarding the object that dominates the _row_. Those actions could be nothing at all, just information to be displayed, or JAVA `AbstractAction`s that execute some code.

Every action should have two keys. One key to indicate what kind of renderer the action will use and, another key to indicate what kind of editor it will use (if any). Dumb actions are provided as `EmptyTellapicNodeAction` objects, used only when an object has less columns data than the tree is displayed.

Why then use a table in the way tables shouldn't be used? Because I can.

There are times when you need to show hierarchical data in some way. They could be related just in parent-child relationship and each object with any kind of information. That information is encapsulated in what I called `TellapicNodeAction`. Not sure why I've chosen that name, but considering that the action of doing nothing is considered an action, and an action is giving you some information, I fullfil the gap between a just-info with info-plus-callbacks cell.

### SUMI is part of the [tellapic project](http://code.google.com/p/tellapic/) and is being developed. There's still no release for SUMI. ###