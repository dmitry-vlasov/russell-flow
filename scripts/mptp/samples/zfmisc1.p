% ---------------------------------------------------------------------------
% Representative MPTP-style (relativized FOL) sample for the ZFMISC_1 MVP.
% Hand-authored to stand in for a real MPTP `fof` export until one is pinned.
% Single-sorted classical FOL: variables uppercase, functors/predicates lower.
% Roles: axiom | definition (-> ru axiom),  theorem | conjecture (-> ru axiom STUB).
% ---------------------------------------------------------------------------

% --- definitions (relativized membership characterisations) ---

fof(d_singleton, definition,
    ! [X,Y] : ( in(Y, singleton(X)) <=> Y = X ) ).

fof(d_subset, definition,
    ! [X,Y] : ( subset(X,Y) <=> ! [Z] : ( in(Z,X) => in(Z,Y) ) ) ).

fof(d_powerset, definition,
    ! [X,Y] : ( in(Y, powerset(X)) <=> subset(Y,X) ) ).

fof(d_union, definition,
    ! [X,Z] : ( in(Z, union(X)) <=> ? [Y] : ( in(Z,Y) & in(Y,X) ) ) ).

% --- a background axiom (extensionality, relativized) ---

fof(ax_extensionality, axiom,
    ! [X,Y] : ( ! [Z] : ( in(Z,X) <=> in(Z,Y) ) => X = Y ) ).

% --- theorems to be transported (emitted as STUB axioms at Milestone 1) ---

fof(t_x_in_singleton_x, theorem,
    ! [X] : in(X, singleton(X)) ).

fof(t_subset_refl, theorem,
    ! [X] : subset(X,X) ).

fof(t_singleton_in_powerset, theorem,
    ! [X] : in(singleton(X), powerset(singleton(X))) ).
