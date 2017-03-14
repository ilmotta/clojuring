# clojuring

[![Build Status](https://travis-ci.org/ilmotta/clojuring.svg?branch=master)](https://travis-ci.org/ilmotta/clojuring)

This README is an attempt to organize the materials I've been using to study
functional programming and Clojure more specifically.

### Tutorial - [Clojure Lab](https://github.com/cognitect/clojure-lab) 

A Clojure workshop by Cognitec, free and open-source. Live (in-browser)
tutorials running in your local machine. Lots of small exercises focusing on
the language fundamentals. I really liked it, but half-way through I just
started reading the theory and not doing the exercises. Actually I felt I
needed more theory before digging deeper in exercises...

The problem with this workshop is that it was built around the idea of a more
or less complete reference. It gets boring real quick... People starting on
Clojure don't need to know all there is about a subject right at the start. For
example, in the chapter about functions we're presented with Java interop,
gotchas, and the anonymous function syntax. They're very lightly touched, but
even then, it would've been better if I was presented with a single way to
define a function and worked with that for a while.

### Book - [Clojure for the Brave and True](http://www.braveclojure.com/getting-started/)

Great introduction to Clojure and it's free to read online. It gives you a
great sense of accomplishment and confidence in the beginning, which is so
important. It introduces Emacs for Clojure development, but I'd recommend
sticking with your preferred editor for a while. I mainly use Vim for software
development, and it's been working great for Clojure too.

I found myself usually skipping the chapter tutorials (almost every chapter has
one). I don't like tutorials for learning unless I'm at the very very beginning.
I recognize this varies a lot between people with different backgrounds.

### Book - [Applied Clojure](https://pragprog.com/book/vmclojeco/clojure-applied)

I really enjoyed this book and highly recommend it after you've been through
the basics and feel confortable with the language. It was writen for people in
the middle stage of learning Clojure. After reading and practing with this book
I started to understand how things fit together in real world libraries.
That's also where I started diving into GitHub repos, macros source code, etc.

I specially liked examples and discussions around state and concurrency.

### Community-driven exercises - [Exercism](http://exercism.io) 

Excellent resource for solving problems while learning about Clojure's core
functions and idioms. You can also see how others solved the same problem, so
you also get a little bit of feedback, like you weren't learning all by
yourself :) It also has similar problems in other languages, so if you know
Javascript, you can solve a problem in Javascript and Clojure.

The key to the Exercism success is that you start with nothing but already
writen unit tests, then it's your job to derive solutions from tests. It
increases the chances that you'll solve the problem, not give up and thus learn
some things.

### YouTube Channel [ClojureTV](https://www.youtube.com/channel/UCaLlzGqiPE2QRj6sSOawJRg)

Seriously, this has been an endless source of material for learning in general.
High-quality talks about a wide range of subjects, mostly Clojure related of
course. I have yet to build a playlist with the best ones!

### YouTube Paid Channel - [Clojure Tutorials, by Timothy Baldridge](https://www.youtube.com/channel/UC6yONKYeoE2P3bsahDtsimg)

Great channel and worth the price to me. This channel is more focused on the
intermediary Clojure developer willing to grasp more advanced concepts, or just
learning Clojure in even more detail. You can experiment for 14 days, and after
that you have to pay to watch non-public videos from the channel.

## Newsletters

It doesn't matter your proficiency with functional programming and Clojure, you
do need to be up-to-date with the latest discussions, libraries, etc involving
the Clojure community. A curated newsletter also keeps me motivated and helps
me find next topics for improvement.

[PurelyFunctional.tv](https://purelyfunctional.tv/newsletter/) is my number one
newsletter at the moment. Eric Normand focuses most of his content on
higher-level discussions, legendary talks, papers (I really like his Computer
Science oriented newsletters). Usually great food for thought beyond just
Clojure as a language. He also runs Clojure workshops and offers on-line
courses. What I don't like is the amount of personal marketing I get in my
inbox, but that's the trade-off.

[The REPL](http://eepurl.com/b6C3KP), a weekly Clojure newsletter, by Daniel
Compton. 

## Tools

I feel productive in Clojure with Vim plus two plugins:
[vim-fireplace](https://github.com/tpope/vim-fireplace) and
[vim-sexp-mappings-for-regular-people](https://github.com/tpope/vim-sexp-mappings-for-regular-people).
It may not be the perfect playground for Clojure, but it's miles better than
nothing.

Although the REPL is an invaluable tool, being able to watch tests in a tmux
split works wonders for me.
[clojure-expectations](https://github.com/clojure-expectations/expectations)
was my test library of choice and has been awesome since the beginning. Super
low learning curve for beginners, and yet very powerful for more advanced
usages.

## Lessons Learned

1. Rewriting code from one language to another is always a challenge and forces
   you to think about language idioms, even more if the original was writen in
   Ruby, Java, C# and the like. I did attempt to rewrite the [GildedRose
   Refactoring
   Kata](https://github.com/ilmotta/GildedRose-Refactoring-Kata/tree/master/clojure/gilded-rose)
   to Clojure and refactor it. Great feeling of accomplishment in the
   beginning!

2. When learning something completely new, people need that feeling of
   discovery, so it's important to introduce new concepts on demand.

3. Get used to read the Clojure docs. Avoid your Stack Overflow instincts. When
   starting to learn a language it's super important to get the fundamentals
   right.  Two sources come to mind: [Clojure Docs](https://clojuredocs.org/)
   (official documentation + lots of examples), and
   [Clojure lang reference](http://clojure.org/reference)
   (good reading for understanding Clojure in more detail, but can be a little
   too dense for starters).

## Other References

- [Lambda Island](https://lambdaisland.com/episodes), Clojure and ClojureScript screencasts. Free trial.
- [Jay Fields' Thoughts](http://blog.jayfields.com/), author of the
  [clojure-expectations](https://github.com/clojure-expectations/expectations)
  library and I've read some pretty good things about Clojure in his blog.
- [Ruby Quiz](http://rubyquiz.com/) - Excellent list of challenging problems. Try to solve some of them in Clojure!
- [Trivial examples of transducers](http://ianrumford.github.io/clojure/transducers/reducers/2014/08/08/Some-trivial-examples-of-using-Clojure-Transducers.html) - What are transducers and how to use them...? I need more references :)
- [Writing Clojure in Vim](https://robots.thoughtbot.com/writing-clojure-in-vim)
