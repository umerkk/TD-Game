# TD-Game
Tower Defence Game
# 					Coding Conventions

1st Author: Lokesh 
2nd Author: Iftikhar
Reviewer: Alaa

# Purpose:
	This document explains the coding conventions to be adhered while developing the SOEN 6441 course project by the team 2 group during the Winter’16 term.

# Source File:
The source code file name should not contain any special characters. The name should be followed by ‘.java’ extension.
This file does not specify anything about other java project related files. But, if the developer has an option, he/she is further requested to use meaningful yet short and crisp names.

# Source Code:
There will not be more than 1 top level class in a source file.
All the member variables / objects should be declared in the beginning of the class before the methods of the class are declared or defined.
If a class has multiple constructors or member methods with same name, they should appear sequentially with no other member(s) in between.
All the sub-blocks enclosed in ‘{‘ between ‘}’ should have the following style:
<method header>/ if(<condition>) / for(<statements>) / while(<condition>) / etc
{
<tab space> <statement>
:
}

All the sub-block statements should be indented using a tab space.
If there is only 1 statement in the sub-block, it need not be enclosed within braces.
All the member method definitions are to be separated by an empty line.
The enum constants may be defined on the same line or optionally in separate lines using a line-break depending on readability.
The variables may be declared as and when required and need not be declared at the start, but the variables must be initialized immediately after declaration.
Even though comments could follow a statement to add meaning to it, no executable statement should appear after the comment. Nevertheless, try to add meaning to the statement using appropriate method / variable / object name instead of using comments to explain the purpose of a method call / variable / object.
Long valued integer literals should use upper case ‘L’ suffix and not ‘l’ to avoid confusion with ‘1’.

# Naming conventions:
Special characters except the ‘_’ is not allowed in file, class, scope, package, method, variable, constant, object or any other names. The ‘_’ is allowed only in below mentioned cases.
The class names should start with an upper case character and followed by lower case characters with words separated by upper case character. 
Every function / method name should be lower character with words separated by an upper character.
All the member objects / variables of a class should have prefix ‘m_’. 
Any global object name should start with a ‘_’.
The constant names should be all upper case character with words separated by ‘_’.
Local variable / object names should be all lower case characters. The parameter names should follow the same format. The names should be short crisp and meaningful.
In the case of local variables / objects with scope limited to a loop or limited scope, 1 or more meaningless lower case characters are allowed.
There is no restriction on using numerals in any names but the names should always start with a character.

# Javadoc:
One blank line i.e., a line containing only the aligned leading asterisk (*) appears between paragraphs, and before the group of "at-clauses" if present. All the paragraphs except the first has <p> at the beginning of the paragraph before the first word, with no space after.
Any of the standard “@” should appear in the following order:
@param, @return, @throws, @deprecated 
and these four types never appear with an empty description.

Reference: 
Google Java Style (https://google.github.io/styleguide/javaguide.html#s4-formatting).

