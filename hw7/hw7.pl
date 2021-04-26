% Jakob Delossantos

% 1
% (a)
    % member helper predicate
    member(X, [X|_]).
    member(X,[_|T]):- member(X, T).

    %Set predicate
    isSet([]). % An empty list is a set
    isSet([H|T]):- not(member(H, T)), isSet(T).% If the head is a member of the tail, then not set. Otherwise, recurse on the rest of the list

% (b)
    isSubset([H|T], X) :- member(H, X), isSubset(T, X).
    isSubset([],_).

% (c)
    unionSets([],[],[]). % Union of two empty sets is empty set
    unionSets(A, [], A). % Union of set and empty set is just A
    unionSets([], A, A). % Union of set and empty set is just A
    unionSets([H|T], B, C):- member(H, B), unionSets(T, B, C). % Check if first list head is in B. If it is, recursive call with tail.
    unionSets([H|T], B, [H|C]):- not(member(H,B)), unionSets(T, B, C). % If first list head is not in B, append to C head.

% (d)
    intersectionSets([], _, []).
    intersectionSets([H|T], B, [H|C]):- member(H, B), intersectionSets(T, B, C). %If H is member of B, then it is member of A and B, so append to C. Recurse
    intersectionSets([_|T], B, C):- intersectionSets(T, B, C). %If it was not a member of B, go to the next    


% 2
    tally(_, [], 0). % Number of occurences in empty list should be 0.
    tally(E, [E|T], N):- !, tally(E, T, S),  N is 1+S. % If head is element we tally
    tally(E, [_|T], S):- tally(E, T, S). % Head was not element so recursive call

% 3
    subst(_, _, [], []). % Base case, empty lists
    subst(X, Y, [X|T], [Y|T2]) :- subst(X, Y, T, T2). % If different, go ahead
    subst(X, Y, [H|T], [H|T2]) :- H\=X, subst(X, Y, T, T2). % If match, change then go ahead

% 4
    insert(X, [], [X]). % If empty list, insert
    insert(X, [H|T], [X,H|T]):- X<H, !. %  You want to insert at the spot before the H if it is less than, and prevent backtracking.
    insert(X, [H|T], [H|T1]):- insert(X, T, T1). % Recursive call because X was not yet less than head of list.

% 5
    flattenList([], []). % Base case, empty lists
    flattenList([H|T], NL) :- flattenList(H, NH), flattenList(T, NT), append(NH, NT, NL). % Recursive call on head and tails which may be their own lists, and append them into a NL (newlist).
    flattenList(L, [L]). % If non-list element, make it a list.

    % append predicate
    append([], L, L).
    append([H|T], L ,[H|L1]):-append(T, L, L1).